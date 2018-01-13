$(document).ready(function () {

    $("#label-form-submit").click(function (event) {
        var label = {};
        label["id"] = $("#id-input").val();
        label["name"] = $("#name-input").val();
        label["yearFounded"] = $("#yearFounded-input").val();

        console.log(label);
        $("#myModal").modal('hide');

        $.ajax({
            type: "post",
            contentType: "application/json",
            url: "/api/label",
            data: JSON.stringify(label),
            dataType: "json",
            success: function (data) {
                console.log(data);
                $("#label").append(new Option(data.name, data.id));
            },
            error: function (e) {
                console.log(e);
            }

        });
    });

    $("#member-form-submit").click(function (event) {
        var member = {};
        member["id"] = $("#id-input").val();
        member["name"] = $("#name-input").val();

        console.log(member);
        $("#myModal").modal('hide');

        $.ajax({
            type: "post",
            contentType: "application/json",
            url: "/api/member",
            data: JSON.stringify(member),
            dataType: "json",
            success: function (data) {
                console.log(data);
                $("#members").append(new Option(data.name, data.id));
            },
            error: function (e) {
                console.log(e);
            }
        });
    });

    $("#genre-form-submit").click(function (event) {
        var genre = {};
        genre["id"] = $("#id-input").val();
        genre["name"] = $("#name-input").val();

        console.log(genre);
        $("#myModal").modal('hide');

        $.ajax({
            type: "post",
            contentType: "application/json",
            url: "/api/genre",
            data: JSON.stringify(genre),
            dataType: "json",
            success: function (data) {
                console.log(data);
                $("#genre").append(new Option(data.name, data.id));
            },
            error: function (e) {
                console.log(e);
            }
        });
    });

    $("#location-form-submit").click(function (event) {
        var location = {};
        location["id"] = $("#id-input").val();
        location["place"] = $("#place-input").val();

        console.log(location);
        $("#myModal").modal('hide');

        $.ajax({
            type: "post",
            contentType: "application/json",
            url: "/api/location",
            data: JSON.stringify(location),
            dataType: "json",
            success: function (data) {
                console.log(data);
                $("#location").append(new Option(data.place, data.id));
            },
            error: function (e) {
                console.log(e);
            }
        });
    });

    $("#promoter-form-submit").click(function (event) {
        var promoter = {};
        promoter["id"] = $("#id-input").val();
        promoter["name"] = $("#name-input").val();
        promoter["yearFounded"] = $("#year-founded-input").val();

        console.log(promoter);
        $("#myModal").modal('hide');

        $.ajax({
            type: "post",
            contentType: "application/json",
            url: "/api/promoter",
            data: JSON.stringify(promoter),
            dataType: "json",
            success: function (data) {
                console.log(data);
                $("#promoter").append(new Option(data.name, data.id));
            },
            error: function (e) {
                console.log(e);
            }
        });
    });

    $("#genre-recommend-submit").click(function (event) {
        console.log("clicked");
        var genre = {};
        genre["id"] = $("#genre").val();

        console.log(genre);

        $.ajax({
            type: "get",
            url: "/api/recommend/" + genre["id"],
            success: function (data) {
                console.log(data);
                if (data.name) $("#recommendation").html("Your recommendation is: <p class='lead'>" + data.name + "</p>");
                else $("#recommendation").text("Unfortunately, we weren't able to find a recommendation for specific genre :(");
            },
            error: function (e) {
                console.log(e);
            }
        });
    });
});