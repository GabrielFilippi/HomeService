document.addEventListener('DOMContentLoaded', function () {
	var idProfissional = $("#div-agenda-eventos").attr("data-id");
	var data = new Date();
	var dia = data.getDate();//dia atual
	var mes = (data.getMonth() + 1); //Pega o mes (index) e soma mais um para pegar o mes correto
	var ano = data.getFullYear();//ano atual

	/*Monta a data*/
	var dataAtual = ano + '-' + mes + '-' + dia;

	//monta o calendario
	var calendarEl = document.getElementById('div-agenda-eventos');
	var calendar = new FullCalendar.Calendar(calendarEl, {
		headerToolbar: {
			left: 'prev,next today',
			center: 'title',
			right: 'dayGridMonth,timeGridWeek,timeGridDay'
		},
		eventTimeFormat: { // like '14:30:00'
			hour: '2-digit',
			minute: '2-digit',
			meridiem: false
		},
		locale: 'pt-br',
		initialDate: dataAtual,
		navLinks: true, // can click day/week names to navigate views
		selectable: true,
		selectMirror: true,
		select: function (arg) { // adicionar evento no dia selecionado
			gerenciarEvent(arg, idProfissional);

			/*
			var title = prompt('Event Title:');
			if (title) {
				calendar.addEvent({
					title: title,
					start: arg.start,
					end: arg.end,
					allDay: arg.allDay
				})
			}
			*/
			calendar.unselect()
		},
		eventClick: function (arg) { // abrir modal com os dados(form) e editalos e salvar, podendo excluir também
			gerenciarEvent(arg, idProfissional);
			/*
			if (confirm('Are you sure you want to delete this event?')) {
				arg.event.remove()
			}
			*/
		},
		editable: true,
		dayMaxEvents: true, // allow "more" link when too many events
	});
	calendar.render();
	buscaDados(idProfissional, mes);

	function gerenciarEvent(arg, idProfissional) {
		var title = "";
		var idAgenda = 0;

		var start = formataData(arg.start)
		var end = formataData(arg.end);
		var description = "";
		$('#delEvento').hide();

		//se o id existir, completa com os dados do banco de dados
		if (arg.event && arg.event.id) {
			idAgenda = arg.event.id;
			title = arg.event.title;
			start = formataData(arg.event.start);
			end = formataData(arg.event.end);
			description = arg.event.extendedProps.description;
			$('#delEvento').show(); //mostra o botao de excluir
		}

		$('#modal-title').html(title);
		$("#agendaId").val(idAgenda)
		$('#agendaProfissionalId').val(idProfissional);
		$('#txtTitulo').val(title);
		$('#dataInicio').val(start);
		$('#dataFinal').val(end);
		$('#dataDescricao').val(description);

		$("#modalAgenda").modal('show');
	}

	function buscaDados(idProfissional, mes) {
		//busca os dados no banco de dados
		$.ajax({
			url: "/minha-conta/profissional/carregarAgenda/" + idProfissional + "/" + mes,
			method: "GET",
			success: function (data) {
				if (data) {
					calendar.removeAllEvents(); //limpa os eventos para evitar duplicacao
					calendar.addEventSource(data);
					calendar.refetchEvents();
				}
			},
			error: function (data) {
				alert(data);
				return null;
			}
		});

	}

	//deleta o evento
	$("#delEvento").on('click', function () {
		var confirm = window.confirm("Deseja excluir este evento?");
		var idAgenda = $("#agendaId").val();
		
		if (confirm && idAgenda) {
			$.ajax({
				url: "/minha-conta/profissional/deleteAgenda/" + idAgenda,
				method: "GET",
				success: function (data) {
					buscaDados(idProfissional, mes);
					$("#modalAgenda").modal('hide');
				},
				error: function (data) {
					alert("Erro ao Excluir Evento!");
					console.log(data);
					return null;
				}
			});
		}
	});

	//quando o usuario clicar nos botos que avançam ou retocedem a data ele atualiza o calendario.
	$('.fc-button').on('click', function () {
		var date = calendar.getDate();
		var mes = date.getMonth() + 1;
		buscaDados(idProfissional, mes);
	});

	//salva a agenda
	$("#formAgenda").submit(function (e) {
		e.preventDefault();
		$.ajax({
			url: "/minha-conta/profissional/salvarAgenda",
			data: $(this).serialize(),
			method: "POST",
			success: function (data) {
				buscaDados(idProfissional, mes);
				$("#modalAgenda").modal('hide');
			},
			error: function (data) {
				alert("Erro ao Adicionar Evento!");
				console.log(data);
				return null;
			}
		});
	});

	//formatar a data em moment
	function formataData(data) {
		return moment(data).format('YYYY-MM-DDTh:mm');
	}

});
