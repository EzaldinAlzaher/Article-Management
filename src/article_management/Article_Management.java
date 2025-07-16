package article_management;

public class Article_Management {

    public static void main(String[] args) {
        ArticleManager as = new ArticleManager(4);

        Article a1 = new Article("Alshareh", "freedomfreedomfreedomfreedomfreedomfreedomfreedomfreedomfreedom", ArticleType.NEWS, "2025-16-07");
        Article a2 = new Article("syria", "freedomfreedomfreedomfreedomfreedomfreedomfreedomfreedomfreedom", ArticleType.NEWS, "2025-16-07");
        Article a3 = new Article("Aljry", "freedomfreedomfreedomfreedomfreedomfreedomfreedomfreedomfreedom", ArticleType.NEWS, "2025-16-07");
        Article a4 = new Article("Albtrik", "freedomfreedomfreedomfreedomfreedomfreedomfreedomfreedomfreedom", ArticleType.NEWS, "2025-16-07");
        Article a5 = new Article("Deepseek", "freedomfreedomfreedomfreedomfreedomfreedomfreedomfreedomfreedom", ArticleType.NEWS, "2025-16-07");
        Article a6 = new Article("Gemini", "freedomfreedomfreedomfreedomfreedomfreedomfreedomfreedomfreedom", ArticleType.NEWS, "2025-16-07");

        as.add("Freedom", a1);
        as.add("Freedom", a2);
        as.add("Freedom", a3);
        as.add("Freedom", a4);
        as.add("AI", a5);
        as.add("AI", a6);

        System.out.println("");

//        DynamicArray dd = search("ez ez ez abdulrahman jasem khalaf hmood ez abdulrahman , . , . , ., . ,");
//        dd.print();

    }

//    public static DynamicArray search(String text) {
//        String[] words = text.toLowerCase().replaceAll("[^a-zA-Z ]", "").split(" ");
//        DynamicArray result = new DynamicArray();
//        int index = 0;
//        for (String word : words) {
////            Word cuWord = result.find(result.findIndex(word)) ? new Word(word, 1) : result.getItem(0);
//            if (result.find(cuWord)) {
//                cuWord.count += 1;
//            } else {
//                result.add(cuWord);
//            }
//        }
//        return result;
//    }

}

class Word {

    String word;
    int count;

    public Word(String word, int count) {
        this.word = word;
        this.count = count;
    }

    @Override
    public String toString() {
        return "-" + word + " : " + count;
    }

}

// -- Article Manager
class ArticleManager {

    LinkedList[] table;
    int size;

//  The count word repeted
    DynamicArray words;

    public ArticleManager(int capacity) {
        size = capacity;
        table = (LinkedList[]) new LinkedList[capacity];
        init();
        words = new DynamicArray();
    }

    // Get Hash
    private int hash(String key) {
        return key.hashCode() % size;
    }

    // Add Article
    public void add(String key, Article value) {
        int index = hash(key);
        table[index].addFirst(value.getTitle(), value);
    }

    public void init() {
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList();
        }
    }

//    DynamicArray searshWordsRepeted() {
//        for (int i = 0; i < size; i++) {
//            Node cuNode = table[i].head;
//            while (cuNode != null) {
//
//                cuNode.value.getContent();
//
//                cuNode = cuNode.next;
//            }
//        }
//    }
}

// -- Article
class Article {

    private String title;
    private String content;
    private ArticleType category;
    private String date;

    public Article(String title, String content, ArticleType category, String date) {
        setTitle(title);
        setContent(content);
        setCategory(category);
        setDate(date);
    }

    // set and get Title
    public void setTitle(String title) {
        if (title.length() >= 2) {
            this.title = title;
        } else {
            System.out.println("Invalid in setTitle.");
        }
    }

    public String getTitle() {
        return title;
    }

    // set and get Content
    public void setContent(String content) {
        if (content.length() >= 50) {
            this.content = content;
        } else {
            System.out.println("Invalid in setContent.");
        }
    }

    public String getContent() {
        return content;
    }

    // set and get Category
    public void setCategory(ArticleType category) {
        this.category = category;
    }

    public ArticleType getCategory() {
        return category;
    }

    // set and get Date
    public void setDate(String date) {
        // 12-08-2024 -> format
        if (date.length() == 10) {
            this.date = date;
        } else {
            System.out.println("Invalid in setDate.");
        }
    }

    public String getDate() {
        return date;
    }

    // Display the Article
    @Override
    public String toString() {
        return "- Title: " + title + ", Category: " + category + ", Date: " + date + "\n Content: " + content;
    }

}

// -- Category of Articles
enum ArticleType {
    LITERARY, // أدبية
    SCIENTIFIC, // علمية
    NEWS, // إخبارية
    ANALYTICAL, // تحليلية
    PERSONAL, // شخصية
    CRITICAL, // نقدية
    CULTURAL, // ثقافية
    ADVISORY // توجيهية
}

// -- Stack
class Stack {

    private Article[] data;
    private int size;
    private int top = -1;

    public Stack(int capacity) {
        data = new Article[capacity];
    }

    public void push(Article item) {
        if (isFull()) {
            return;
        }
        data[++top] = item;
        size++;
    }

    public Article pop() {
        if (isEmpty()) {
            return null;
        }
        Article popped = data[top];
        data[top--] = null;
        size--;
        return popped;
    }

    public Article peek() {
        if (isEmpty()) {
            return null;
        }
        return data[top];
    }

    public boolean isFull() {
        return size == data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        while (!isEmpty()) {
            pop();
        }
    }

    public void print() {
        while (!isEmpty()) {
            System.out.println(pop());
        }
    }
}

// -- LinkedList 
class LinkedList {

    Node head;
    int size;

//    Add item in first 
    public void addFirst(String key, Article item) {
        Node newNode = new Node(key, item);
        newNode.next = head;
        head = newNode;
        size++;
    }

//    Insert item by index
//    public void insert(K key, V item, int index) {
//        Node newNode = new Node(key, item);
//        if (index < 0 || index > size) {
//            System.out.println("Error in insert!");
//        } else if (index == 0) {
//            addFirst(key, item);
//        } else if (index == size) {
//            addLast(key, item);
//        } else {
//            Node curr = head;
//            Node nCurr;
//            for (int i = 0; i < index - 1; i++) {
//                curr = curr.next;
//            }
//            nCurr = curr.next;
//            curr.next = newNode;
//            newNode.next = nCurr;
//            size++;
//        }
//    }
//    Delete item in first 
    public void deleteFirst() {
        if (size == 0) {
            System.out.println("Error in Delete First!");
        } else {
            Node curr = head;
            head = head.next;
            curr.next = null;
            size--;
        }
    }

//    Delete item in last
    public void deleteLast() {
        Node curr = head;
        if (size == 0) {
            System.out.println("Error in Delete Last!");
        } else if (size == 1) {
            deleteFirst();
        } else {
            while (curr.next.next != null) {
                curr = curr.next;
            }
            curr.next = null;
            size--;
        }
    }

//    Delete item By index 
    public void delete(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Error in Delete item by index!");
        } else if (index == 0) {
            deleteFirst();
        } else if (index == size - 1) {
            deleteLast();
        } else {
            Node curr = head;
            Node deleted;
            Node nCurr;
            for (int i = 0; i < index - 1; i++) {
                curr = curr.next;
            }
            deleted = curr.next;
            nCurr = deleted.next;
            curr.next = nCurr;
            size--;
        }
    }

//    Delete item from all list 
    public void deleteItem(Article item) {
        Node curr = head;
        int idx = 0;
        while (curr != null) {
            if (curr.value.equals(item)) {
                delete(idx);
            } else {
                idx++;
            }
            curr = curr.next;
        }
    }

//    Clear the list
    public void clear() {
        while (size != 0) {
            deleteFirst();
        }
    }

//    Get item by index --10
    public Article get(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Error in get item!");
            return null;
        } else {
            Node curr = head;
            for (int i = 0; i < index; i++) {
                curr = curr.next;
            }
            return curr.value;
        }
    }

//    Get count item in list --13
//    public int getCountItem(V item) {
//        Node<K, V> curr = head;
//        int result = 0;
//        while (curr != null) {
//            if (curr.value == item) {
//                result++;
//            }
//            curr = curr.next;
//        }
//        return result;
//    }
//    Get index of first item --17
//    public int indexOf(V item) {
//        Node curr = head;
//        for (int i = 0; i < size; i++) {
//            if (curr.value == item) {
//                return i;
//            }
//            curr = curr.next;
//        }
//        return -1;
//    }
    public boolean find(Article item) {
        Node curr = head;
        while (curr != null) {
            if (curr.value == item) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

//    Is empty 
    public boolean isEmpty() {
        return size == 0;
    }

//    Print 
    public void print() {
        Node curr = head;
        for (int i = 0; i < size; i++) {
            System.out.println(curr.value);
            curr = curr.next;
        }
    }

}

class Node {

    String key;
    Article value;
    Node next;

    public Node(String key, Article value) {
        this.key = key;
        this.value = value;
    }

}

// dynamic array
class DynamicArray {

    Word[] data;
    int size = 0;

    public DynamicArray() {
        data = new Word[10];
    }

    public DynamicArray(int cabacity) {
        data = new Word[cabacity];
    }

    public void add(Word item) {
        if (data.length == size) {
            enchoreCabacity(size * 2);
        }
        data[size] = item;
        size++;
    }

    public Word getItem(int index) {
        return data[index];
    }

    public int getSize() {
        return size;
    }

    public int getLinght() {
        return data.length;
    }

    public void print() {
        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i]);
        }
    }

    public void enchoreCabacity(int len) {
        Word[] newData = (Word[]) new Object[len];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    public void incert(Word item, int index) throws Exception {
        if (index < 0 || index > size) {
            throw new Exception("invaled index");
        }
        if (data.length == size) {
            enchoreCabacity(size * 2);
        }

        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = item;
        size++;
    }

    public void delete(int index) throws Exception {
        if (size == 0) {
            throw new Exception("The arr is notEmpity");
        }
        if (index < 0 || index > size) {
            throw new Exception("invaled index");
        }
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        if (size == data.length / 2) {
            enchoreCabacity(data.length / 2);
        } else {
            data[size] = null;
        }

    }

    public int findIndex(Word item) {

        for (int i = 0; i < size; i++) {
            if (item.equals(data[i])) {
                return i;
            }
        }
        return -1;
    }

    public boolean find(Word item) {
        for (int i = 0; i < size; i++) {
            if (item.equals(data[i])) {
                return true;
            }
        }
        return false;
    }

//    public Search<T> findv2(T item) {
//        Search<T> res = new Search<T>();
//        for (int i = 0; i < size; i++) {
//            if (item == data[i]) {
//                res.index = i;
//                res.item = item;
//                break;
//            }
//        }
//        return res;
//    }
    public boolean isExist(Word item) {
        boolean result = false;
        for (int i = 0; i < size; i++) {
            if (data[i] == item) {
                return true;
            }
        }
        return result;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    public void addArray(Word[] arr) {
        if (size + arr.length >= data.length) {
            enchoreCabacity(size * 2);
        }
        int arrIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            data[size] = arr[i];
            size++;
        }
    }

    public void finDelete(Word item) throws Exception {
//        boolean find=false;
        for (int i = size; i >= 0; i--) {
            if (data[i] == item) {
                this.delete(i);
            }
        }
    }

    public void trimToSize() {
        if (size < data.length) {
            enchoreCabacity(size);
        }
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int countItem(Word item) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (data[i] == item) {
                count++;
            }
        }
        return count;
    }
}
