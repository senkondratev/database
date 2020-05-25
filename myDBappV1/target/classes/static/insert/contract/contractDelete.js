$(document).on("click", "#deleteContract", function () {
    var id = $(this).attr('value');
    let flag = confirm("Удаление связанной сущности может привести к каскадному удалению, продолжить?");
    if (flag){
        $.post('/insert/contractDelete',{
            contractId : id
        }, function (html) {
            jQuery('#table').html(html);
        });
    }

});