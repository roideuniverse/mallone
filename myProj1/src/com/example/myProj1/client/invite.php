<?php
 $postEmails= $_POST['jsvalue'];
 $emails= explode(":",$postEmails);
 foreach($emails as &$email)
 {
	$To=$email;
	$Subject="Test--mallone -- InVitation ";
	$mesg = "Hi, welcome! \n http://www.mallone.in ";
	$headers = "From: k.aditya@mallone.in" . "\r\n" . "CC: k.saurabh@mallone.in" ;
//      	if(mail($email, $Subject, $mesg, $headers))
//	{
    		echo "Invitation successfully sent to ". $To . "<br/>";
//	}
 }
?>
<SCRIPT LANGUAGE="JavaScript">
<!--hide
   //window.close();
   setTimeout("window.close()",3000);
//-->
</SCRIPT>