var myMessages = ['info','warning','error','success']; // define the messages types         
function hideAllMessages()
{
     var messagesHeights = new Array(); // this array will store height for each
 
     for (i=0; i<myMessages.length; i++)
     {
              messagesHeights[i] = $('.' + myMessages[i]).outerHeight();
              $("#" + myMessages[i] + "-message").css('margin-top', -messagesHeights[i]); //move element outside viewport      
         }
}

function showMessage(type, title, message, timeout)
{
  hideAllMessages();
  $("#" + type + "-message").find("h3").text(title);
  $("#" + type + "-message").find("p").text(message);
  $("#" + type + "-message").animate({ "margin-top":"0"}, 500);
  
  setTimeout(function(){
      $("#" + type + "-message").click()
    }, timeout);
}

$(document).ready(function(){
     
     // Initially, hide them all
     hideAllMessages();
     
     // When message is clicked, hide it
     $('.message').click(function(){              
              $(this).animate({marginTop: -$(this).outerHeight()}, 500);
      });         
     
}); 