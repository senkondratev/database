$(document).on("click", "#deleteCompany", function () {
    var id = $(this).attr('value');
    let flag = confirm("Удаление связанной сущности может привести к каскадному удалению, продолжить?");
    if (flag){
        $.post('/insert/companyDelete',{
            companyId : id
        }, function (html) {
            jQuery('#table').html(html);
        });
    }

});