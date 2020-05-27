$(document).on("change", "#reservationSelect", function () {
    var selected = $(this).val();
    $.get("/insert/guestValidate",
        {
            selectedReservationId : selected
        }, function(html){
            jQuery('#roomSelect').html(html);
        });
})