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


println ("Found ${sipMessages.size()}  messages")
sipMessages.each {println it}