package com.example.demo.designMode.garnisher;

/**
 * @author: YJY
 * @date: 2023/6/9 9:57
 * @description:
 */
public abstract class Decorator extends Component {

  protected Component component;

  public void SetComponent(Component component) {

    this.component = component;
  }

  @Override
  public void operation() {

    if (null != component) {
      component.operation();
    }
  }
}
