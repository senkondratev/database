$(document).on("click", "#deleteClient", function () {
    var id = $(this).attr('value');
    let flag = confirm("Удаление связанной сущности может привести к каскадному удалению, продолжить?");
    if (flag){
        $.post('/insert/clientDelete',{
            clientId : id
        }, function (html) {
            jQuery('#table').html(html);
        });
    }

});