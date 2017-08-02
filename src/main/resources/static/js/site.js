function fetchSuccess(data) {

    var container = $("#image-container");

    $("#image-container").empty();

    // display new content
    $("#image-container-title").html(data.feed.title);

    $(data.feed.entry).each(function(idx, obj){
        var url = obj.link[0]['href'];
        var src = obj.link[1]['href'];

        var url, src, links = obj.link;

        for(var i=0; i<links.length; i++) {
            if (links[i]["href"] == "alternate")
                url = links[i]["rel"]
            else if (links[i]["rel"] == "enclosure")
                src = links[i]["href"]
        }

        container.append('<a href="' + url + '" target="_blank"><img src="' + src + '" /></a>');
    });
}

function fetchError(xhr, error) {
    console.log(error);
}

function handleSubmitJ() {

    var tags = document.getElementById("tags").value;
    tags = tags.replace(' ', '');

    $.get({
        url: "/search/tags/"+tags,
        success: fetchSuccess,
        error: fetchError,
        dataType: 'json'
    });

    return false;
}

$(document).ready(function(){
    $('form').submit(handleSubmitJ);
});
