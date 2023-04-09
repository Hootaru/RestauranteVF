$(document).ready(function() {
    $('#calendar').fullCalendar({
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,basicWeek,basicDay'
        },
        defaultView: 'month',
        editable: true,
        eventLimit: true, // allow "more" link when too many events
        events: [
            {
                title: 'My Event',
                start: '2023-04-05'
            }
            // more events here
        ]
    });
});
