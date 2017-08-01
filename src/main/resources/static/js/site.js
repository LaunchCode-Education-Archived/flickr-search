function fetchSuccess(data) {

    var container = $("#image-container");

    $("#image-container").empty();

    // display new content
    $("#image-container-title").html(data.title);

    $(data.entry).each(function(idx, obj){
        var url = obj.link[0]['@attributes']['href'];
        var src = obj.link[1]['@attributes']['href'];
        container.append('<a href="' + url + '" target="_blank"><img src="' + src + '" /></a>');
    });
}

function fetchError(xhr, error) {
    console.log(error);
}

function handleSubmitJ() {

    var tags = document.getElementById("tags").value;
    tags = tags.replace(' ', '');

    $.ajax("/web-examples/js/jquery/flickr/get_flickr_data.php", {
        data: {tags: tags},
        type: "POST",
        success: fetchSuccess,
        error: fetchError,
        dataType: 'json'
    });

    return false;
}

$(document).ready(function(){
    $('form').submit(handleSubmitJ);
});
