function setOnlyNumber() 
{    
	var keyCode = event.keyCode;    
    if ((keyCode >= 48 && keyCode <= 57))    
    {    
        event.returnValue = true;    
    } else {    
        event.returnValue = false;    
    }    
 }

function setMoreNumber() 
{    
	var keyCode = event.keyCode;    
    if ((keyCode >= 48 && keyCode <= 57)||(keyCode==46))    
    {    
        event.returnValue = true;    
    } else {    
        event.returnValue = false;
    }    
 }