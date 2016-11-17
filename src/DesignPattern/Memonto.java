package DesignPattern;

/**
 * [��Ϊģʽ]
 * ����¼ģʽ����¼����ǰ��״̬���Ա��ں����״̬�ָ���Ҳ����undo.
 * Originator����Ҫ������״̬�Ķ���Memonto����˽���ڲ��࣬Caretaker���ڰ�����¼Originator��״̬.
 * ����¼��ֻ�ܱ�Originator����ʣ�Caretaker���ڱ���ͻָ�Originator��״̬.
 * ʵ��������Caretaker��Memonto��ռ���ر����ڴ棬����һ��ʹ��Serialization��������ƵĹ���.
 * */

/** Originator ���ػ����� */
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

	// write������֤���ı�����д
	public void write(String str) {
		content.append(str);
	}

	// save�Ǳ��浱ǰ״̬������һ��˽���ڲ�����󣺱���¼����
	public Memento save() {
		return new Memento(this.fileName, this.content);
	}

	// undo������Ҫ���ָ��Ķ���Ȼ��ָ���ǰ״̬.
	public void undoToLastSave(Object obj) {
		Memento memento = (Memento) obj;
		this.fileName = memento.fileName;
		this.content = memento.content;
	}

	/** ����¼�� */
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

/** Caretaker �ػ����࣬���ڱ��淵�صı���¼���� */
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
