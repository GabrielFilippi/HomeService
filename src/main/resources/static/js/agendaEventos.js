document.addEventListener('DOMContentLoaded', function () {

	var data = new Date();
	var dia = data.getDate();//dia atual
	var mes = (data.getMonth() + 1); //Pega o mes (index) e soma mais um para pegar o mes correto
	var ano = data.getFullYear();//ano atual

	/*Monta a data*/
	var dataAtual = ano + '-' + mes + '-' + dia;

	var calendarEl = document.getElementById('div-agenda-eventos');

	var calendar = null;

	
	$.ajax({
		url: "/conta-profissional/carregarAgenda/" + $("#div-agenda-eventos").attr("data-id"),
		method: "GET",
		success: function (data) {
			console.log(data);
			calendar = new FullCalendar.Calendar(calendarEl, {
				headerToolbar: {
					left: 'prev,next today',
					center: 'title',
					right: 'dayGridMonth,timeGridWeek,timeGridDay'
				},
				initialDate: dataAtual,
				navLinks: true, // can click day/week names to navigate views
				selectable: true,
				selectMirror: true,
				select: function (arg) {
					var title = prompt('Event Title:');
					if (title) {
						calendar.addEvent({
							title: title,
							start: arg.start,
							end: arg.end,
							allDay: arg.allDay
						})
					}
					calendar.unselect()
				},
				eventClick: function (arg) {
					if (confirm('Are you sure you want to delete this event?')) {
						arg.event.remove()
					}
				},
				editable: true,
				dayMaxEvents: true, // allow "more" link when too many events
				events: data
			});
		
			calendar.render();
			

		}
	})
});
