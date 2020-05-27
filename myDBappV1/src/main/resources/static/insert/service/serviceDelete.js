$(document).on("click", "#deleteService", function () {
    var id = $(this).attr('value');
    let flag = confirm("Удаление связанной сущности может привести к каскадному удалению, продолжить?");
    if (flag){
        $.post('/insert/serviceDelete',{
            serviceId : id
        }, function (html) {
            jQuery('#table').html(html);
        });
    }

});