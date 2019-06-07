package ua.edu.ukma.amukhopad.parallels.dispatch;

import java.util.Map;

import ua.edu.ukma.amukhopad.parallels.MessageType;
import ua.edu.ukma.amukhopad.parallels.util.CommUtil;
import ua.edu.ukma.amukhopad.parallels.util.ProcessorUtil;

public class Executor<T> implements Runnable {
  private volatile Map<Integer, T> tasksResult;
  private Class<T> dataType;

  private Executor(Class<T> dataType, Map<Integer, T> tasksResult) {
    this.tasksResult = tasksResult;
    this.dataType = dataType;
  }

  public static <D> Executor<D> create(Class<D> dataType, Map<Integer, D> tasksResult) {
    return new Executor<>(dataType, tasksResult);
  }

  public static <D> Thread start(Class<D> dataType, Map<Integer, D> tasksResult) {
    Thread thread = ProcessorUtil.startDaemon(Executor.create(dataType, tasksResult));
    Log.exec("Executor Started");
    return thread;
  }

  @Override
  public void run() {
    while (true) {
      Message<T> result = CommUtil.listen(MessageType.TASK_RETURN, dataType);
      tasksResult.put(result.getSource(), result.getData());


    }
  }
}
