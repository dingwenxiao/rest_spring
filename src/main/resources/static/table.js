$(document).ready(function(){
var $TABLE = $('#table');
var $BTN = $('#export-btn');
var $EXPORT = $('#export');

$.get("/getAllRecords", function( data ) {
$.each( data, function( key, value ) {
  $('table tr:last').after('<tr><td>'+ value.value + '</td><td></td><td><span class="table-remove glyphicon glyphicon-remove"></span></td><td><button id=' + value.value +' class="btn btn-info btn-xs" data-toggle="modal" data-target="#myModal">show details</button></td></tr>');
});

});


$('.table-add').click(function () {
   var value = $('#input').val();
  $('table tr:last').after('<tr><td>'+ value + '</td><td></td><td><span class="table-remove glyphicon glyphicon-remove"></span></td><td><button class="btn btn-info btn-xs" data-toggle="modal" data-target="#myModal">show details</button></td></tr>');

   var json = { 'value': value, 'isPalindrome': "false" , 'date': new Date().getDate()};
     $.ajax({
  method: "POST",
  url: "/addRecord",
  data: JSON.stringify(json),
  dataType: "json",
  contentType: 'application/json'
}).done(function( msg ) {
    console.log( "Data Saved: " + msg );
  });
});

$('.table-search').click(function () {
  var value = $('#input').val();
  $('table').find("tr:gt(0)").remove();
$.get("/record/" + value, function( data ) {
 $('table tr:last').after('<tr><td>'+ value + '</td><td></td><td><span class="table-remove glyphicon glyphicon-remove"></span></td><td><button class="btn btn-info btn-xs" data-toggle="modal" data-target="#myModal">show details</button></td></tr>');
});

});

$('table').on('click', 'tr td span.table-remove', function(){
    var text = $(this).parent().prev('td').prev('td').text();
  $(this).parents('tr').detach();
  $.ajax({
    url: '/remove/' + text,
    type: 'DELETE',
    success: function(result) {
        console.log("deleted successfully");
    }
});
});

$('#myModal').on('show.bs.modal', function (e) {
  var _self = $(this);
  var value = e.relatedTarget.id;

$.get("/record/" + value, function( data ) {
  $('.modal-body p').text("isPalindrome:" + data.isPalindrome + " date:" + data.date);
});

})

// $('.table-remove').click(function () {
//   var text = $(this).parent().prev('td').prev('td').text();
//   $(this).parents('tr').detach();
//   $.ajax({
//     url: '/remove/' + text,
//     type: 'DELETE',
//     success: function(result) {
//         console.log("deleted successfully");
//     }
// });
// });


// A few jQuery helpers for exporting only
jQuery.fn.pop = [].pop;
jQuery.fn.shift = [].shift;

$BTN.click(function () {
  var $rows = $TABLE.find('tr:not(:hidden)');
  var headers = [];
  var data = [];
  
  // Get the headers (add special header logic here)
  $($rows.shift()).find('th:not(:empty)').each(function () {
    headers.push($(this).text().toLowerCase());
  });
  
  // Turn all existing rows into a loopable array
  $rows.each(function () {
    var $td = $(this).find('td');
    var h = {};
    
    // Use the headers from earlier to name our hash keys
    headers.forEach(function (header, i) {
      h[header] = $td.eq(i).text();   
    });
    
    data.push(h);
  });
  
  // Output the result
  $EXPORT.text(JSON.stringify(data));
});

});

