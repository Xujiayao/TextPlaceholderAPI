package eu.pb4.placeholders.api.node;

import eu.pb4.placeholders.api.ParserContext;
import net.minecraft.text.NbtDataSource;
import net.minecraft.text.Text;

import java.util.Optional;

public record NbtNode(String rawPath, boolean interpret, Optional<TextNode> separator,
                      NbtDataSource dataSource) implements TextNode {
	@Override
	public Text toText(ParserContext context, boolean removeBackslashes) {
		//#if MC > 11802
		return Text.nbt(rawPath, interpret, separator.map(x -> x.toText(context, removeBackslashes)), dataSource);
		//#else
		//$$ return Text.of("");
		//#endif
	}

	@Override
	public boolean isDynamic() {
		return separator.isPresent() && separator.get().isDynamic();
	}
}
