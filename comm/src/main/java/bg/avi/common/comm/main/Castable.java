package bg.avi.common.comm.main;

import java.util.Collection;
import java.util.List;

/**
 * <h1>Add descr...</h1>
 * 
 * @author Alexander Verbovskiy
 * @version 1.0
 * @since 2020-03-28
 */
public interface Castable<T, S> {
	public T cast(S object);
	public List<T> cast(Collection<S> object);
}
