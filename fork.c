#include <sys/types.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

int main(int argc, char** argv){

//fork a child process
int cpuEmulatorChild, schedularChild;

cpuEmulatorChild = fork();

//if pid is less then zero, then child was not created
if(cpuEmulatorChild < 0){
  perror("Fork() error");
}

// only the child process executes codes here
if(cpuEmulatorChild == 0) {
    execl("/usr/bin/java","/usr/bin/java", "-cp", "cpuEmulator.jar", "cpuEmulator", NULL);
    perror("Error with emulator");
}
// only parent executes
else{

  //wait for child one to complete its process before child 2 is created
  wait(NULL);

  //fork the second child from original parent
  schedularChild = fork();

  //error making second child
  if(schedularChild < 0){
    perror("Second Fork() error");
    return 1;
  }

  if(schedularChild == 0){
    printf("Child 2 PID: %d\n", getpid());
    execl("/usr/bin/java","/usr/bin/java", "-cp", "schedular.jar", "schedular", NULL);
    perror("Error with schedular");

  }

  else{
  //parent is waiting for all children to execute
  wait(NULL);
  printf("This is Parent PID: %d\n",  getpid());

  }
}

return 0;

}
