$(document).ready(function() {

    $('.page-num').click(function() {
        $('table').find("tr:gt(0)").remove();
        var pageNum = $(this).text() - 1;
        $.get("/getRecords?start=" + pageNum * 10 + "&&offset=10&&value=", function(data) {
            $.each(data, function(key, value) {
                $('table tr:last').after('<tr><td class="col-md-6" data-id=' + value.id + '>' + value.value + '</td><td class="col-md-3"><span class="table-remove glyphicon glyphicon-remove"></span></td><td class="col-md-3"><button id=' + value.id + ' class="btn btn-info btn-xs" data-toggle="modal" data-target="#myModal">show details</button></td></tr>');
            });
        });
    });
});