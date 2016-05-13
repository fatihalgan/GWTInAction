package org.gwtbook.client.ui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ChangeListenerCollection;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasWordWrap;
import com.google.gwt.user.client.ui.KeyboardListenerAdapter;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MouseListener;
import com.google.gwt.user.client.ui.SourcesChangeEvents;
import com.google.gwt.user.client.ui.SourcesClickEvents;
import com.google.gwt.user.client.ui.SourcesMouseEvents;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class EditableLabel extends Composite implements HasWordWrap, HasText,
	HasHorizontalAlignment, SourcesClickEvents, SourcesChangeEvents, SourcesMouseEvents {

	private TextBox changeText;
	private TextArea changeTextArea;
	private Label text;
	
	private String originalText;
	
	private Widget confirmChange;
	private Widget cancelChange;
	
	private boolean isEditing = false;
	private boolean isEditable = true;
	
	ChangeListenerCollection changeListeners;
	
	private String defaultOKButtonText = "OK";
	private String defaultCancelButtonText = "Cancel";
	
	public EditableLabel(String labelText) {
		createEditableLabel(labelText, defaultOKButtonText, defaultCancelButtonText);
	}
	
	public EditableLabel(String labelText, String okText, String cancelText) {
		createEditableLabel(labelText, okText, cancelText);
	}
	
	public EditableLabel(String labelText, boolean wordWrap) {
          createEditableLabel(labelText, defaultOKButtonText, defaultCancelButtonText);
          text.setWordWrap(wordWrap);
    }

	public EditableLabel(String labelText, String okText, String cancelText, boolean wordWrap) {
        createEditableLabel(labelText, okText, cancelText);
        text.setWordWrap(wordWrap);
    }
	
	private void createEditableLabel(String labelText, String okButtonText, String cancelButtonText) {
		FlowPanel instance = new FlowPanel();
		text = new Label(labelText);
		text.setStyleName("editableLabel-label");
		
		text.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				changeTextLabel();
			}
		});
		
		changeText = new TextBox();
		changeText.setStyleName("editableLabel-textBox");
		changeText.addKeyboardListener(new KeyboardListenerAdapter() {
			public void onKeyPress(Widget sender, char keyCode, int modifiers) {
				// If return then save, if Esc cancel the change, otherwise do nothing
                switch (keyCode) {
                	case 13:
                		setTextLabel();
                		break;
                    case 27:
                    	cancelLabelChange();
                        break;
                }
			}	
		});
		
		changeTextArea = new TextArea();
		changeTextArea.setStyleName("editableLabel-textArea");
		changeTextArea.addKeyboardListener(new KeyboardListenerAdapter() {
			public void onKeyPress(Widget sender, char keyCode, int modifiers) {
				// If Esc then cancel the change, otherwise do nothing
                switch (keyCode) {
                    case 27:
                        cancelLabelChange();
                        break;
                }
			}
		});
		
		confirmChange = createConfirmButton(okButtonText);
		if(!(confirmChange instanceof SourcesClickEvents)) {
			throw new RuntimeException("Confirm change button must allow for click events");
		}
		
		((SourcesClickEvents)confirmChange).addClickListener(new ClickListener() {
			public void onClick (Widget sender) {
				setTextLabel();
			}
		});
		
		cancelChange = createCancelButton(cancelButtonText);
		if (!(cancelChange instanceof SourcesClickEvents)) {
            throw new RuntimeException("Cancel change button must allow for click events");
        }
        
        ((SourcesClickEvents)cancelChange).addClickListener(new ClickListener() {
            public void onClick (Widget sender) {
                cancelLabelChange();
            }
        });
        
        FlowPanel buttonPanel = new FlowPanel();
        buttonPanel.setStyleName("editableLabel-buttonPanel");
        buttonPanel.add(confirmChange);
        buttonPanel.add(cancelChange);
        
        instance.add(text);
        instance.add(changeText);
        instance.add(changeTextArea);
        instance.add(buttonPanel);
        
        text.setVisible(true);
        changeText.setVisible(false);
        changeTextArea.setVisible(false);
        confirmChange.setVisible(false);
        cancelChange.setVisible(false);

        text.setWordWrap(false);
        
        //Always call this method in composite widgets
        initWidget(instance);
	}
	
	protected Widget createConfirmButton(String okButtonText) {
		Button result = new Button();
		result.setStyleName("editableLabel-buttons");
		result.addStyleName("editableLabel-confirm");
		result.setText(okButtonText);
		return result;
	}
	
	protected Widget createCancelButton(String cancelButtonText) {
		Button result = new Button();
		result.setStyleName("editableLabel-buttons");
		result.addStyleName("editableLabel-cancel");
		result.setText(cancelButtonText);
		return result;
	}
	
	
	public void setEditable(boolean flag) {
		isEditable = flag;
	}
	
	public boolean isFieldEditable() {
		return isEditable;
	}
	
	public boolean isInEditingMode() {
		return isEditing;
	}
	
	private void changeTextLabel() {
		if(isEditable) {
			originalText = text.getText();
			text.setVisible(false);
			confirmChange.setVisible(true);
			cancelChange.setVisible(true);
			
			if(text.getWordWrap()) {
				changeTextArea.setText(originalText);
				changeTextArea.setVisible(true);
				changeTextArea.setFocus(true);
			} else {
				changeText.setText(originalText);
				changeText.setVisible(true);
				changeText.setFocus(true);
			}
			isEditing = true;
		}
	}
	
	private void restoreVisibility() {
		text.setVisible(true);
		confirmChange.setVisible(false);
		cancelChange.setVisible(false);
		if(text.getWordWrap()) {
			changeTextArea.setVisible(false);
		} else {
			changeText.setVisible(false);
		}
		isEditing = false;
	}
	
	private void setTextLabel() {
		if(text.getWordWrap()) {
			text.setText(changeTextArea.getText());
		} else {
			text.setText(changeText.getText());
		}
		restoreVisibility();
		// Call the update method provided in the Constructor
        // (this could be anything from alerting the user through to
        // Making an AJAX call to store the data.
        //updater.onChange(this);
        if (changeListeners!=null)changeListeners.fireChange(this);
	}
	
	public void cancelLabelChange() {
		text.setText(originalText);
		restoreVisibility();
	}

	public int getVisibleLength() {
		if(text.getWordWrap()) {
			throw new RuntimeException("Cannnot get visible length for a word-wrapped Editable Label");
		} else {
			return changeText.getVisibleLength();
		}
	}
	
	public void setVisibleLength(int length) {
		if (text.getWordWrap()){
            throw new RuntimeException("Cannnot set visible length for a word-wrapped Editable Label");
        } else {
            changeText.setVisibleLength(length);
        }
	}
	
	public int getMaxLength() {
		if (text.getWordWrap()){
            return changeTextArea.getCharacterWidth();
        } else {
            return changeText.getMaxLength();   
        }
	}
	
	public void setMaxLength(int maxLength) {
		if (text.getWordWrap()){
            changeTextArea.setCharacterWidth(maxLength);
        } else {
            changeText.setMaxLength(maxLength);    
        }
	}
	
	public int getVisibleLines() {
		if (text.getWordWrap()) {
			return changeTextArea.getVisibleLines();    
        } else {
            throw new RuntimeException("Editable Label that is not word-wrapped has no number of Visible Lines");
        }       
    }
	
	public void setVisibleLines(int number) {
        if (text.getWordWrap()){
            changeTextArea.setVisibleLines(number); 
        } else {
            throw new RuntimeException("Cannnot set number of visible lines for a non word-wrapped Editable Label");
        }
    }
	
	public boolean getWordWrap() {
		return text.getWordWrap();
	}

	public void setWordWrap(boolean wrap) {
		text.setWordWrap(wrap);		
	}

	public String getText() {
		return text.getText();
	}

	public void setText(String text) {
		this.text.setText(text);		
	}

	public HorizontalAlignmentConstant getHorizontalAlignment() {
		return text.getHorizontalAlignment();
	}

	public void setHorizontalAlignment(HorizontalAlignmentConstant align) {
		text.setHorizontalAlignment(align);		
	}

	public void addClickListener(ClickListener listener) {
		this.text.addClickListener(listener);		
	}

	public void removeClickListener(ClickListener listener) {
		this.text.removeClickListener(listener);		
	}

	public void addChangeListener(ChangeListener listener) {
		if(changeListeners == null) {
			changeListeners = new ChangeListenerCollection();
		}
		changeListeners.add(listener);
	}

	public void removeChangeListener(ChangeListener listener) {
		if(changeListeners != null) {
			changeListeners.remove(listener);
		}
	}

	public void addMouseListener(MouseListener listener) {
		this.text.addMouseListener(listener);		
	}

	public void removeMouseListener(MouseListener listener) {
		this.text.removeMouseListener(listener);		
	}
	
	
}
