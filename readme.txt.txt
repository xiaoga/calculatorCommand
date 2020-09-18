project module

1. server：
	server main starter. get input string and call facade. 
2. stringAnalyzer： 
	analyze different grammar，change input command into internal data model.
3. commandProcessor： 
	deal with command using internal data model.
	i will use command mode here to ensure it is easy to extend , to add different method.
4. major facade and impl：
	the entrance of calculator and the implementation of that. it will control the major flow.
	i will use template mode so that it will be possible to build the template and possible to change template.

major process chain
server -> facade -> analyzer -> command

major data model
external command -> internal command -->stack --> process result --> stack





