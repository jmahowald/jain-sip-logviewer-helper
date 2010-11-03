/**
 * Usage groovy JainLog4JSipExtractor [regex_in_sip_to_search_for]
 */

def inSipMessage = false
def searchRegex = ".*"
if(args.length > 0)
{
	searchRegex = args[0]
}
def buff = new StringBuffer()

def sipMessages = []


System.in.eachLine() { line->
	if(line =~ /<message/)
	{
		inSipMessage = true
		buff = new StringBuffer()
	}
	else if(line =~ /<\/message/)
	{
		inSipMessage = false
		
		if(buff =~ searchRegex)
		{
			sipMessages << buff
		}
	}
	else if(inSipMessage) {
		buff.append(line + "\n")
	}
	
}


//Outputs the needed 'headers' for the tracesviewer
String HEADER="""

<!-- Use the  Trace Viewer in src/tools/tracesviewer to view this  trace
	Here are the stack configuration properties
	javax.sip.STACK_NAME= Mobicents-SIP-Servlets
	-->
<description logDescription="Mobicents-SIP-Servlets"  name="Mobicents-SIP-Servlets" auxInfo="null"/>
"""
sipMessages.each {sipMessage ->
	println("<message>")
	println sipMessage
	println("</sipMessage")	
}


