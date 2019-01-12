package com.basic.framework.common.utils.excel.editor.font;

import com.basic.framework.common.utils.excel.editor.IFontEditor;
import com.basic.framework.common.utils.excel.style.font.Font;

/**
 * 实现一些常用的字体<br/>
 * 该类用于设置斜体
 * 
 * @author zxh
 * 
 */
public class ItalicFontEditor implements IFontEditor {

	@Override
    public void updateFont(Font font) {
		font.italic(true);
	}

}
