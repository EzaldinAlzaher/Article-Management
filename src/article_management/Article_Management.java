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
    }

}

// -- Article Manager
class ArticleManager{

    LinkedList[] table;
    int size;

    public ArticleManager(int capacity) {
        size = capacity;
        table = (LinkedList[]) new LinkedList[capacity];
        init();
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

}

// -- Article
class Article{

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
