$(document).ready(function() {
    var $TABLE = $('#table');
    var $BTN = $('#export-btn');
    var $EXPORT = $('#export');

    $.get("/getRecords?start=0&&offset=10&&value=", function(data) {
        $.each(data, function(key, value) {
            $('table tr:last').after('<tr><td data-id=' + value.id + '>' + value.value + '</td><td><span class="table-remove glyphicon glyphicon-remove"></span></td><td><button id=' + value.id + ' class="btn btn-info btn-xs" data-toggle="modal" data-target="#myModal">show details</button></td></tr>');
        });
    });


    $('.table-add').click(function() {
        var value = $('#input').val();
        //  $('table tr:last').after('<tr><td data-id=' + + '>' + value + '</td><td><span class="table-remove glyphicon glyphicon-remove"></span></td><td><button id=' + value + ' class="btn btn-info btn-xs" data-toggle="modal" data-target="#myModal">show details</button></td></tr>');
        var now = moment().utc().format('YYYY-MM-DD HH:mm:ss');
        var json = {
            'id': 'dummy_id',
            'value': value,
            'isPalindrome': "false",
            'date': now
        };
        $.ajax({
            method: "POST",
            url: "/addRecord",
            data: JSON.stringify(json),
            dataType: "json",
            contentType: 'application/json'
        }).done(function(data) {
            console.log("Data Saved: " + data);
            $('table tr:last').after('<tr><td class="col-md-6" data-id=' + data.id + '>' + data.value + '</td><td class="col-md-3"><span class="table-remove glyphicon glyphicon-remove"></span></td><td class="col-md-3"><button id=' + data.id + ' class="btn btn-info btn-xs" data-toggle="modal" data-target="#myModal">show details</button></td></tr>');
        });
    });

    $('.table-search').click(function() {
        var value = $('#input').val();
        $('table').find("tr:gt(0)").remove();
        $.get("/getRecords?start=0&&offset=10&&value=" + value, function(data) {
            $.each(data, function(key, value) {
                $('table tr:last').after('<tr><td class="col-md-6" data-id=' + value.id + '>' + value.value + '</td><td class="col-md-3"><span class="table-remove glyphicon glyphicon-remove"></span></td><td class="col-md-3"><button id=' + value.id + ' class="btn btn-info btn-xs" data-toggle="modal" data-target="#myModal">show details</button></td></tr>');
            });
        });

    });

    $('table').on('click', 'tr td span.table-remove', function() {
        var text = $(this).parent().prev('td').attr('data-id');
        $(this).parents('tr').detach();
        $.ajax({
            url: '/remove/' + text,
            type: 'DELETE',
            success: function(result) {
                console.log("deleted successfully");
            }
        });
    });

    $('#myModal').on('show.bs.modal', function(e) {
        var _self = $(this);
        var value = e.relatedTarget.id;
        $.get("/record/" + value, function(data) {
            console.log(data.date);
            $('.modal-body p').text("It is palindrome? " + (data.isPalindrome ? "yes" : "no") + " - created date: " + data.date);
        });

    })

});