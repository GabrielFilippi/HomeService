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
		locale: 'pt-br',
		initialDate: dataAtual,
		navLinks: true, // can click day/week names to navigate views
		selectable: true,
		selectMirror: true,
		select: function (arg) { // adicionar evento no dia selecionado
			gerenciarEvent(arg);

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
			gerenciarEvent(arg);
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

	function gerenciarEvent(arg){
		var title = "";
		var start = "";
		var end = "";
		var description = "";

		if(arg.title){
			title = arg.title;
			start = arg.start;
			end = arg.end;
			description = arg.description;

			$('#delEvento').show();
		}

		$('#modal-title').html(title);
		$('#txtTitulo').html(title);
		$('#dataInicio').html(start);
		$('#dataFinal').html(end);
		$('#dataDescricao').html(description);
		
		$("#modalAgenda").modal('show');
	}

	function buscaDados(idProfissional, mes) {
		//busca os dados no banco de dados
		$.ajax({
			url: "/conta-profissional/carregarAgenda/" + idProfissional + "/" + mes,
			method: "GET",
			success: function (data) {
				if(data){
					calendar.addEventSource(data);
					calendar.refetchEvents();
				}
			},
			error: function(data){
				alert(data);
				return null;
			}
		});

	}

	//deleta o evento
	$("#delEvento").on('click', function () {
		var confirm = confirm("Deseja excluir este evento?");

		if(confirm){
			//exclui
		}
	});

	//quando o usuario clicar nos botos que avançam ou retocedem a data ele atualiza o calendario.
	$('.fc-button').on('click', function () {
		var date = calendar.getDate();
		var mes = date.getMonth()+1;
		buscaDados(idProfissional, mes);
	});

});
