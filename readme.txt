# environment
jdk 8，maven 3.6.3，idea 2020.2

# product features
## about the function
you can input the number and operator in console , system will calculate and display the result.

## detail features

so far it support +, -, *, /, sqrt, clear, undo

+: 1+1 =2

-: 2-1 =1

*: 2*3 =6

/: 6/2 =3

sqrt: 4 sqrt=2

clear: clear all the element you input. i.e. if you input 1,2,3,4,5,clear the result will be ""(empty)

undo: will undo the last operate. i.e. if you do 2 3 *,result will be 6. if you undo right after that, result will be 2 3.

# system components introduction

there are there major components in this system

1. application IOC container

2. calculater facade

3. command funcion

## 1. application IOC container

it is a very simple container , include ApplicationContext and RegisterBeanEnum.

### how does it work

RegisterBeanEnum: 

you need to register all the service which you need into this Enum, so system can automatically load beans when start.

ApplicationContext: 

there is a context inside this class. all the instance will be saved there. 

there is an init method. when it is called, system will automatically start container and init bean.

there is a contextStack, which is used to save input and output.

there is a undoStack, which is used to save undo info so that "undo" can be done.


### todo

if have time i will add annotation to mark which classes need to be create and which bean need to be reference injected that will give developer better experience, and the experience will super close to spring.

## 2. calculater facade

it is the entrance of calculator engine. there are only a few logic inside.
i use template pattern to do this part , define flow in abstract class and implement in detail class. it will be possible for us to add some other way to implement this flow via change template or add new detail class.
 
### how does it work

AbstractCalculatorFacade: 

preProcess: do something before process

postProcess: do something after process

process: deal with major logic . in our case i just call command engine.


## 3. calculater command engine

it is the core function model in this system which deal with all the detail logic of the product.
i use command pattern to solve the problem. each method is a command , all the command should be registered in OperateCommandEnum. so if you want to add one more function just add a command and register .

### how does it work

OperateCommandEnum: all the input command and detail command class mapping are here. it will help system understand which command should be used when receive input.

commandExecutor: it is the interface of the command engine.

CommandExecutorImpl: it implement the interface dealing with the logic. it will identify whether the command belongs to the operation or input. if it is operation, it will use the right command to solve the problem. if it is just a input thing, that will be done by it.

CalculateCommand: that is the interface of command all the command will implement that.

AbstractCalculateCommand: there are some common functions located in this class.

Command*: these are the detail command which deal with the detail logic.


# about the test cases

i also write some test cases to ensure the functions are well. please check "test" package.
apart from that i worte all the cases following the "Example" , see below.

======Example1=======

[5, 2]

======Example2=======

[1.4142135623730951454746218587388284504413604736328125]

======Example3=======

[3]

[0]

======Example4=======

[5, 4, 3, 2]

[20]

[100]

[20, 5]

======Example5=======

[7, 6]

[42]

[10.5]

======Example6=======

[1, 2, 3, 4, 5]

[1, 2, 3, 20]

[-1]

======Example7=======

[1, 2, 3, 4, 5]

[120]

======Example8=======

com.jay.calculator.service.exception.ServiceException: insufficient numbers! 
