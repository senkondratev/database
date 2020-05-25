$(document).on("click", "#deleteReservation", function () {
    var id = $(this).attr('value');
    let flag = confirm("Удаление связанной сущности может привести к каскадному удалению, продолжить?");
    if (flag){
        $.post('/insert/reservationDelete',{
            reservationId : id
        }, function (html) {
            jQuery('#table').html(html);
        });
    }

});