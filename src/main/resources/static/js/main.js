$(document).ready(function(){
    $('.table .ePenyakitBtn').on('click', function (event) {
        $('.penyakitForm #penyakitModal').modal();
    });

    $('.table .delBtn').on('click', function(event){
        event.preventDefault();
        var href = $(this).attr('href');
        $('#myModal #delRef').attr('href',href);
        $('#myModal').modal();
    });
});