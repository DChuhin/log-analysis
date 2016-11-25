# log-analysis

All config is in application.properties file
param.group.username, param.input.dir, param.thread.count, param.output.file params must be specified, other are optional.

The app scans param.input.dir for all .log files (including subdirectories). 
If any exception occurs during file processing (illegal file records structure) this file will be skipped.

Expected .log files record:
<username>,<dateTime as string, default iso format>,<message>:
Example:
userName,2016-11-02T00:01:02,message

Running app: run as usual java console app, no any args are needed, all params are defined in application.properties
