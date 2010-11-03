Jain SIP Extratcor
============
by Josh Mahowad <mahowald@gmail.com>

*Aids in parsing log files generated by the jain SIP stack
*Uses very useful, (though raw) traces viewer from jain team at:
http://users.cis.fiu.edu/~ege/JAIN/tools/tracesviewer/README.html

Installation
------------

  - Git clone the workspace

    `git clone https://github.com/jmahowald/jain-sip-logviewer-helper.git`

Usage
------------


    `groovy JainLog4JSipExtractor.groovy  [regex_in_sip_to_search_for] < logContents > tracescompliant.log`
    `java -jar -server_file tracescompliant.log`


Thanks
------------
To  Vadim Snitkovsy <vsnitkovsky@angel.com> for providing all the info on how to use tracesviewer
