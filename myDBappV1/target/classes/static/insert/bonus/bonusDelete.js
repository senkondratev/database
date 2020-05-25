$(document).on("click", "#deleteBonus", function () {
    var id = $(this).attr('value');
    let flag = confirm("Удаление связанной сущности может привести к каскадному удалению, продолжить?");
    if (flag){
        $.post('/insert/bonusDelete',{
            bonusId : id
        }, function (html) {
            jQuery('#table').html(html);
        });
    }

});