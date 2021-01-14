import ui.StartPage;

public class App {
    private static StartPage START_PAGE;

    private static void initPages() {
        START_PAGE = new StartPage();
    }

    public static void main(String[] args) throws Exception {
        initPages();

        START_PAGE.show();

        START_PAGE.getSubmitButton().addActionListener(e -> {
            System.out.println(START_PAGE.getUsernameInput().getText());
        });
    }
}
