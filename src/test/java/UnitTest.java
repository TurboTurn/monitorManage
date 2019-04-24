import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author : ys
 * @date : 2019/4/24 9:27 星期三
 **/
public class UnitTest {
	@Test
	public void t1() throws InterruptedException {
		ArrayList<Integer> list = new ArrayList<>();
		int a=300;
		for(int i=0;i<7;i++){
			list.add(a);
			a+=50;
		}
		System.out.println(list.toString());

		ArrayDeque<Integer> deque = new ArrayDeque<>();
		int b=300;
		for(int i=0;i<7;i++){
			deque.addLast(b);
			b+=50;
		}
		while (true){
			System.out.println(deque.toString());
			deque.addLast(deque.removeFirst()+new Random().nextInt(300));

			TimeUnit.SECONDS.sleep(1);

		}

	}
}
