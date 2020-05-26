$(document).on("click", "#deleteBuilding", function () {
    var id = $(this).attr('value');
    let flag = confirm("Удаление связанной сущности может привести к каскадному удалению, продолжить?");
    if (flag){
        $.post('/insert/buildingDelete',{
            buildingId : id
        }, function (html) {
            jQuery('#table').html(html);
        });
    }

});