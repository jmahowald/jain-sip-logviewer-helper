/**
 * Usage groovy JainLog4JSipExtractor -s [regex_in_sip_to_search_for] -v [regex_in_sip_to_exclude] < logfile > sipmessage.log
 */

def cli = new CliBuilder()
cli.with {
     usage: 'Self'
	 s longOpt:'searchRegex', 'a regex to search for within a sip message for instance a particualr call id or particular ip',args:1
	 v longOpt:'execludeRegex', 'a regex to ignore in sip messages, i.e. OPTIONS', args:1
     h longOpt:'help', 'usage information'
     i longOpt:'input', 'input file', args:1
}
def opt = cli.parse(args)

if( opt.h ) {
    cli.usage()
    return
}

def input = System.in

if( opt.i ) {
	input = new File(opt.i)
}
def searchRegex = ".*"
if(opt.s)
{
	searchRegex = opt.s
}
def excludeRegex
if(opt.v)
{
	excludeRegex = opt.v
}




def inSipMessage = false
def buff = new StringBuffer()


//Outputs the needed 'headers' for the tracesviewer
String HEADER="""
<!-- Use the  Trace Viewer in src/tools/tracesviewer to view this  trace
Here are the stack configuration properties
javax.sip.IP_ADDRESS= null
javax.sip.STACK_NAME= Mobicents-SIP-Servlets
javax.sip.ROUTER_PATH= null
javax.sip.OUTBOUND_PROXY= null
-->
<description logDescription="Mobicents-SIP-Servlets"  name="Mobicents-SIP-Servlets" auxInfo="null"/>
"""

println HEADER


input.eachLine() { line->
	if(line =~ /<message/)
	{
		inSipMessage = true
		buff = new StringBuffer()
	}
	else if(line =~ /<\/message/)
	{
		inSipMessage = false
		if(buff =~ searchRegex && !(excludeRegex && buff =~ excludeRegex))
		{
			println("<message")
			println buff
			println("</message>")
		}
	}
	else if(inSipMessage) {
		buff.append(line + "\n")
	}
	
}




