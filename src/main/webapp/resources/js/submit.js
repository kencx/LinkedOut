
// submit textarea input when enter is pressed
// newline when shift+enter is pressed

$(document).ready(function() {    
    $("#textarea").keypress(function (e) {
        
		if(e.which === 13 && !e.shiftKey) {
            e.preventDefault();
            $(this).closest("form").submit();
        }
    });
});