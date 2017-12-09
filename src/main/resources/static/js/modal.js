$(document).ready(function () {
    $('#newGenreBtn').on('click', function (e) {
        $("#myModal").modal();
        $("#myModalBody").text("");
        $.ajax({
            url: "/genre/new",
            cache: false
        }).done(function (html) {
            $("#myModalBody").append(html);
        });
    });
    $('#newMemberBtn').on('click', function (e) {
        $("#myModal").modal();
        $("#myModalBody").text("");
        $.ajax({
            url: "/member/new",
            cache: false
        }).done(function (html) {
            $("#myModalBody").append(html);
        });
    });
    $('#newLabelBtn').on('click', function (e) {
        $("#myModal").modal();
        $("#myModalBody").text("");
        $.ajax({
            url: "/label/new",
            cache: false
        }).done(function (html) {
            $("#myModalBody").append(html);
        });
    });
    $('#newLocationBtn').on('click', function (e) {
        $("#myModal").modal();
        $("#myModalBody").text("");
        $.ajax({
            url: "/location/new",
            cache: false
        }).done(function (html) {
            $("#myModalBody").append(html);
        });
    });
    $('#newPromoterBtn').on('click', function (e) {
        $("#myModal").modal();
        $("#myModalBody").text("");
        $.ajax({
            url: "/promoter/new",
            cache: false
        }).done(function (html) {
            $("#myModalBody").append(html);
        });
    });
});