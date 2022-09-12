package fr.alexandreguiny.lastdayonearth;

import fr.alexandreguiny.lastdayonearth.item.Parameter;
import fr.alexandreguiny.lastdayonearth.item.Stat;
import fr.alexandreguiny.lastdayonearth.variable.Category;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// TODO add scroll
public abstract class  MyPanel extends JPanel {
    private final JTabbedPane categories = new JTabbedPane();
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private final ArrayList<Parameter> parameterList = new ArrayList<>();
    private final JPanel all = new JPanel();
    private final Function1<? super Parameter, Stat> constructor;

    public MyPanel(@NotNull Function1<? super Parameter, Stat> constructor) {
        this.constructor = constructor;
        this.setLayout(new BorderLayout());
        this.add(categories, BorderLayout.CENTER);
        all.setLayout(new FlowLayout());
        categories.add(all, "All");
        categories.addChangeListener(e -> subUpdate());
        for (var category : Category.class.getEnumConstants()) {
            var panel = new JPanel();
            panel.setLayout(new FlowLayout());
            categories.add(panel, category.name());
        }
    }

    public void update() {
        parameterList.clear();
        for (var parameter : Parameter.parameters) {
            if (filter(parameter)) {
                parameterList.add(parameter);
                all.add(map(parameter));
            }
        }
        subUpdate();
    }

    public void subUpdate() {
        var panel = (JPanel) categories.getSelectedComponent();
        var title = categories.getTitleAt(categories.getSelectedIndex());
        panel.removeAll();
        if (title.equals("All")) {
            for(var stat : Stat.Companion.getStats()) {
                panel.add(stat);
            }
        } else {
            for(var stat : Stat.Companion.getStats()) {
                if (stat.getParameter().getCategory().equals(Category.of(title)))
                    panel.add(stat);
            }
        }
    }

    protected Stat map(Parameter parameter) {
        return constructor.invoke(parameter);
    }

    protected abstract boolean filter(Parameter item);
}
