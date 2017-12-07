package tmp.others;

import java.util.concurrent.TimeUnit;

import sun.misc.Signal;
import sun.misc.SignalHandler;

/**
 * 
 * java中Signal枚举有哪些各代表什么含义：

USR1:用户自定义信号1
USR2:用户自定义信号2

推荐：用户自定义信号USR1和USR2,其他信号我同事说可能会隐患别的问题,最好不用.

TERM:终止信号

KILL:Kill信号

INT:键盘中断

HUP :终端挂起或者控制进程终止

BUS：总线错误

上面这5个出现的比较多，下面是其他的信号

ALRM:警告

CHLD：子进程结束信号

CONT:进程继续（曾被停止的进程）

FPE:浮点异常

ILL:非法指令

IO:某I/O操作现在可以进行了

IOT:IO捕获指令

PIPE:管道破裂: 写一个没有读端口的管道

PROF:Profiling定时器到

PWR:电源故障

QUIT:键盘的退出键被按下

SEGV:无效的内存引用

STKFLT:协处理器堆栈错误

STOP:终止进程
TRAP:跟踪/断点捕获

TSTP:控制终端（tty）上按下停止键

TTIN:后台进程企图从控制终端读

TTOU:后台进程企图从控制终端写

VTALRM: 实际时间报警时钟信号

WINCH:窗口大小改变

XCPU:超出设定的CPU时间限制

XFSZ:超出设定的文件大小限制
 * @author yuan
 *
 */
public class LogTest {
/**
 * 在Linux下支持的信号（具体信号kill -l命令查看）：
SEGV, ILL, FPE, BUS, SYS, CPU, FSZ, ABRT, INT, TERM, HUP, USR1, USR2, QUIT, BREAK, TRAP, PIPE
在Windows下支持的信号：
SEGV, ILL, FPE, ABRT, INT, TERM, BREAK
 * */
	public static void main(String[] args)throws Exception {
		// ctrl-c
		Signal.handle(new Signal("INT"), new SignalHandler() {
			@Override
			public void handle(Signal arg0) {
				System.out.println("INT");
			}
		});
		// kill -15
		Signal.handle(new Signal("TERM"), new SignalHandler() {
			@Override
			public void handle(Signal arg0) {
				System.out.println("TERM");
			}
		});
		// kill -10
//		Signal.handle(new Signal("USR1"), new SignalHandler() {
//			@Override
//			public void handle(Signal arg0) {
//				System.out.println("USR1");
//			}
//		});
//		// kill -12
//		Signal.handle(new Signal("USR2"), new SignalHandler() {
//			@Override
//			public void handle(Signal arg0) {
//				System.out.println("USR2");
//			}
//		});
		
		// BREAK
//		Signal.handle(new Signal("BREAK"), new SignalHandler() {
//			@Override
//			public void handle(Signal arg0) {
//				System.out.println("BREAK");
//			}
//		});
		
		TimeUnit.MINUTES.sleep(10);
	}

}
