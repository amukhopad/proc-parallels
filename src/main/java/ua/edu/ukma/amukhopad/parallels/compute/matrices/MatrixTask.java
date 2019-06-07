package ua.edu.ukma.amukhopad.parallels.compute.matrices;

import java.io.Serializable;

public interface MatrixTask extends Serializable {
  int getBlockSize();
}
