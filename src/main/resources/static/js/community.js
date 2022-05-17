//提交回复
function post() {
    var discussionId = $("#discussion_id").val();
    var content = $("#comment_content").val();
    comment2target(discussionId,1,content);
}

function comment(e){
    var commentId = e.getAttribute("data-id");
    var content = $("#input-"+commentId).val();
    comment2target(commentId,2,content);
}

function comment2target(targetId,type,content){
    if(!content){
        alert("不能回复空内容！");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            "parentID": targetId,
            "content": content,
            "type": type
        }),
        success: function (response) {
            if (response.code == 200) {
                window.location.reload();
            } else {
                if (response.code == 2003) {
                    var isAccepted = confirm(response.message);
                    if (isAccepted) {//页面无法传递元素
                        window.open("https://gitee.com/oauth/authorize?client_id=a2b730b14a7047c3f50ce9a1cadb18fcc02b7e0b894a146457d33ea13e63f5db&redirect_uri=http://localhost/callback/gitee&response_type=code")
                        //存到浏览器里面
                        window.localStorage.setItem("closable",true);
                    }
                } else {
                    alert(response.message);

                }
            }
        },
        dataType: "json"
    });
}


//展开二级评论
function collapseComments(e){
    var id=e.getAttribute("data-id");
    var comments=$("#comment-"+id);
    var collapse = e.getAttribute("data-collapse");
    if (collapse) {
        // 折叠二级评论
        comments.removeClass("show");
        e.removeAttribute("data-collapse");
        e.classList.remove("active");
    } else {
        var subCommentContainer = $("#comment-" + id);
        if (subCommentContainer.children().length != 2) {
            //展开二级评论
            comments.addClass("show");
            // 标记二级评论展开状态
            e.setAttribute("data-collapse", "show");
            e.classList.add("active");
        } else {
            $.getJSON("/comment/" + id, function (data) {
                console.log(data);
                $.each(data.data.reverse(), function (index, comment) {
                    var mediaIdElement = $("<div/>", {
                        "class": "media-Id"
                    }).append($("<img/>", {
                        "class": "img-thumbnail rounded-3 h-100 d-inline-block",
                        "src": comment.user.avatarUrl
                    })).append($("<div/>", {
                        "class": "align-content-center d-inline-block",
                        "html": comment.user.name
                    }));

                    var mediaBodyElement = $("<div/>", {
                        "class": "media-body"
                    }).append($("<div/>", {
                        "html": comment.content
                    })).append($("<span/>", {
                        "class": "comment-menu float-end",
                        "html": moment(comment.gmtCreate).format('YYYY-MM-DD')
                    }));

                    var mediaElement = $("<div/>", {
                        "class": "media"
                    }).append(mediaIdElement).append(mediaBodyElement);

                    var commentElement = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comments"
                    }).append(mediaElement);

                    subCommentContainer.prepend(commentElement);
                });
                // 展开二级评论
                comments.addClass("show");
                // 标记二级评论展开状态
                e.setAttribute("data-collapse", "show");
                e.classList.add("active");
            });
        }
    }
}

function showSelectTag() {
    $("#select-tag").show();
}


function selectTag(e) {
    var value = e.getAttribute("data-tag");
    var previous = $("#tag").val();
    if (previous.indexOf(value) == -1) {
        if (previous) {
            $("#tag").val(previous + ',' + value);
        } else {
            $("#tag").val(value);
        }
    }
}