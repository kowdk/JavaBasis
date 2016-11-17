package DesignPattern;

/**
 * [行为模式]
 * 备忘录模式：记录对象当前的状态，以便于后面的状态恢复，也就是undo.
 * Originator是需要被保存状态的对象，Memonto是其私有内部类，Caretaker用于帮助记录Originator的状态.
 * 备忘录类只能被Originator类访问，Caretaker用于保存和恢复Originator的状态.
 * 实际中由于Caretaker和Memonto会占用特别大的内存，所以一般使用Serialization来完成类似的功能.
 * */

/** Originator 被守护者类 */
class FileWriterUtil {

	private String fileName;
	private StringBuilder content;

	public FileWriterUtil(String file) {
		this.fileName = file;
		this.content = new StringBuilder();
	}

	@Override
	public String toString() {
		return this.content.toString();
	}

	// write函数保证向文本池中写
	public void write(String str) {
		content.append(str);
	}

	// save是保存当前状态，返回一个私有内部类对象：备忘录对象。
	public Memento save() {
		return new Memento(this.fileName, this.content);
	}

	// undo接收需要被恢复的对象，然后恢复当前状态.
	public void undoToLastSave(Object obj) {
		Memento memento = (Memento) obj;
		this.fileName = memento.fileName;
		this.content = memento.content;
	}

	/** 备忘录类 */
	private class Memento {
		private String fileName;
		private StringBuilder content;

		public Memento(String file, StringBuilder content) {
			this.fileName = file;
			// notice the deep copy so that Memento and FileWriterUtil content
			// variables don't refer to same object
			this.content = new StringBuilder(content);
		}
	}
}

/** Caretaker 守护者类，用于保存返回的备忘录对象 */
class FileWriterCaretaker {

	private Object obj;

	public void save(FileWriterUtil fileWriter) {
		this.obj = fileWriter.save();
	}

	public void undo(FileWriterUtil fileWriter) {
		fileWriter.undoToLastSave(obj);
	}
}

public class Memonto {
	public static void main(String[] args) {

		FileWriterCaretaker caretaker = new FileWriterCaretaker();

		FileWriterUtil fileWriter = new FileWriterUtil("E:\\data.txt");
		fileWriter.write("First Set of Data\n");
		System.out.println(fileWriter + "\n\n");

		// lets save the file
		caretaker.save(fileWriter);
		// now write something else
		fileWriter.write("Second Set of Data\n");

		// checking file contents
		System.out.println(fileWriter + "\n\n");

		// lets undo to last save
		caretaker.undo(fileWriter);

		// checking file content again
		System.out.println(fileWriter + "\n\n");

	}
}
