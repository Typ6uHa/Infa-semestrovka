$(document).ready(function(){
    $(document).on('click', '.glyphicon-star-empty', function(e){
        var topic_id = $(this).attr('value');
        $.ajax({
            url: "/ajax-favorite",
            data: {"id" : topic_id, "action": "add"},
            success: function (result) {
                $(this).removeClass('glyphicon-star-empty').addClass('glyphicon-star');//if ajax success
            }
        })

    });
    $(document).on('click', '.glyphicon-star', function(e){
        var topic_id = $(this).attr('value');
        $.ajax({
            url: "/ajax-favorite",
            data: {"id" : topic_id, "action": "remove"},
            success: function (result) {
                $(this).removeClass('glyphicon-star').addClass('glyphicon-star-empty'); //if ajax success
            }
        })

    });
});
$(document).ready(function(){
    $(document).on('click', '.glyphicon-star-empty', function(e){
        $(this).removeClass('glyphicon-star-empty').addClass('glyphicon-star');
    });
    $(document).on('click', '.glyphicon-star', function(e){
        $(this).removeClass('glyphicon-star').addClass('glyphicon-star-empty');
    });
});
