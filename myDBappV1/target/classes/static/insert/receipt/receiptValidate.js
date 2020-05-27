$(document).on("change", "#guestSelect", function () {
    var selected = $(this).val();
    $.get("/insert/receiptValidate",
        {
            selectedGuestId : selected
        }, function(html){
            jQuery('#serviceSelect').html(html);
        });
})