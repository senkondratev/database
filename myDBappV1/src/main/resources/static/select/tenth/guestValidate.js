$(document).on("change", "#roomSelect", function () {
    var selected = $(this).val();
    $.get("/select/tenthValidate",
        {
            selectedRoomId : selected
        }, function(html){
            jQuery('#guestSelect').html(html);
        });
})