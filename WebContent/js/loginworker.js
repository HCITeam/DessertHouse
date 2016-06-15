$("#workerLogin").click(function() 
{
	playWait("#workerLogin");
	$("#workerPost").submit();
});

function playWait(widget)
{
	$(widget).html("<div class='loadShow'><img class='loadImg' src='../img/load.png' alt='O'></div>");
	$(widget).attr("disabled", true);
}