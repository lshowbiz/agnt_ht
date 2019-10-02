package com.joymain.jecs.webapp.resource;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.core.Messages;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.context.HierarchicalMessageSource;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.util.ClassUtils;
import org.springframework.util.ObjectUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.bean.ContextUtil;
import com.joymain.jecs.webapp.util.LocaleUtil;

/**
 * 多语言处理接口,适应于extremeTable及Spring
 * @author Aidy.Q
 * 
 */
public class MessageResourceBundle implements Messages, HierarchicalMessageSource, BeanClassLoaderAware {
	private final Log logger = LogFactory.getLog(MessageResourceBundle.class);
	private Locale locale;
	private String characterCoding;

	/* =========extremeTable使用=========== */
	public String getMessage(String code) {
		return getMessageFull(code, null, code);
	}

	public String getMessage(String code, Object[] args) {
		return getMessageFull(code, args, code);
	}

	private String getMessageFull(String code, Object[] args, String defaultMessage) {
		this.characterCoding = ((SysUser) ContextUtil.getResource("sessionLogin")).getDefCharacterCoding();
		String result = LocaleUtil.getLocalText(characterCoding, code, defaultMessage);

		if (!StringUtils.isEmpty(result) && args != null) {
			MessageFormat formatter = new MessageFormat("");
			formatter.setLocale(locale);
			formatter.applyPattern(result);
			result = formatter.format(args);
		}

		return result;
	}

	public void init(Context context, Locale locale) {
		characterCoding = ((SysUser) ContextUtil.getResource("sessionLogin")).getDefCharacterCoding();
		this.locale = locale;
	}

	/* =========spring使用=========== */
	private MessageSource parentMessageSource;

	public void setParentMessageSource(MessageSource parent) {
		this.parentMessageSource = parent;
	}

	public MessageSource getParentMessageSource() {
		return parentMessageSource;
	}

	public final String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
		String msg = getMessageInternal(code, args);
		if (msg != null) {
			return msg;
		}
		if (defaultMessage == null) {
			return null;
		}
		return renderDefaultMessage(defaultMessage, args);
	}

	public final String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
		String msg = getMessageInternal(code, args);
		if (msg != null) {
			return msg;
		}

		return code;
		//throw new NoSuchMessageException(code, locale);
	}

	public final String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
		String[] codes = resolvable.getCodes();
		if (codes == null) {
			codes = new String[0];
		}
		for (int i = 0; i < codes.length; i++) {
			String msg = getMessageInternal(codes[i], resolvable.getArguments());
			if (msg != null) {
				return msg;
			}
		}
		if (resolvable.getDefaultMessage() != null) {
			return renderDefaultMessage(resolvable.getDefaultMessage(), resolvable.getArguments());
		}
		if (codes.length > 0) {
			return null;
		}
		throw new NoSuchMessageException(codes.length > 0 ? codes[codes.length - 1] : null, locale);
	}

	/**
	 * 根据键值及参数返回对应的信息, 如果没有则返回null
	 * @param code 字符键值
	 * @param args 字符值中需要使用的参数集
	 * @return 信息, 如果没有则返回null
	 * @see #getMessage(String, Object[], String, Locale)
	 * @see #getMessage(String, Object[], Locale)
	 * @see #getMessage(MessageSourceResolvable, Locale)
	 */
	protected String getMessageInternal(String code, Object[] args) {
		if (code == null) {
			return null;
		}
		Object[] argsToUse = args;

		if (ObjectUtils.isEmpty(args)) {
			String message = resolveCodeWithoutArguments(code);
			if (message != null) {
				return message;
			}
		} else {
			argsToUse = resolveArguments(args);

			MessageFormat messageFormat = resolveCode(code);
			if (messageFormat != null) {
				return messageFormat.format(argsToUse);
			}
		}

		return getMessageFromParent(code, argsToUse);
	}

	/**
	 * Try to retrieve the given message from the parent MessageSource, if any.
	 * @param code the code to lookup up, such as 'calculator.noRateSet'
	 * @param args array of arguments that will be filled in for params within the message
	 * @param locale the Locale in which to do the lookup
	 * @return the resolved message, or <code>null</code> if not found
	 * @see #getParentMessageSource()
	 */
	protected String getMessageFromParent(String code, Object[] args) {
		MessageSource parent = getParentMessageSource();
		if (parent != null) {
			if (parent instanceof AbstractMessageSource) {
				// Call internal method to avoid getting the default code back
				// in case of "useCodeAsDefaultMessage" being activated.
				return getMessageInternal(code, args);
			} else {
				return parent.getMessage(code, args, null, locale);
			}
		}
		return null;
	}

	/**
	 * Render the given default message String. The default message is passed in as specified by the caller and can be rendered into a fully formatted default message shown to the user.
	 * <p>
	 * Default implementation passes the String to <code>formatMessage</code>, resolving any argument placeholders found in them. Subclasses may override this method to plug in custom processing of default messages.
	 * @param defaultMessage the passed-in default message String
	 * @param args array of arguments that will be filled in for params within the message, or <code>null</code> if none.
	 * @param locale the Locale used for formatting
	 * @return the rendered default message (with resolved arguments)
	 * @see #formatMessage(String, Object[], java.util.Locale)
	 */
	protected String renderDefaultMessage(String defaultMessage, Object[] args) {
		return formatMessage(defaultMessage, args);
	}

	/**
	 * Format the given message String, using cached MessageFormats. By default invoked for passed-in default messages, to resolve any argument placeholders found in them.
	 * @param msg the message to format
	 * @param args array of arguments that will be filled in for params within the message, or <code>null</code> if none.
	 * @param locale the Locale used for formatting
	 * @return the formatted message (with resolved arguments)
	 */
	protected String formatMessage(String msg, Object[] args) {
		if (msg == null) {
			return msg;
		}
		MessageFormat messageFormat = createMessageFormat(msg);

		return messageFormat.format(resolveArguments(args));
	}

	/**
	 * Create a MessageFormat for the given message and Locale.
	 * <p>
	 * This implementation creates an empty MessageFormat first, populating it with Locale and pattern afterwards, to stay compatible with J2SE 1.3.
	 * @param msg the message to create a MessageFormat for
	 * @param locale the Locale to create a MessageFormat for
	 * @return the MessageFormat instance
	 */
	protected MessageFormat createMessageFormat(String msg) {
		MessageFormat messageFormat = new MessageFormat("");
		if (msg != null) {
			messageFormat.applyPattern(msg);
		}
		return messageFormat;
	}

	/**
	 * Search through the given array of objects, find any MessageSourceResolvable objects and resolve them.
	 * <p>
	 * Allows for messages to have MessageSourceResolvables as arguments.
	 * @param args array of arguments for a message
	 * @return an array of arguments with any MessageSourceResolvables resolved
	 */
	protected Object[] resolveArguments(Object[] args) {
		if (args == null) {
			return new Object[0];
		}
		List resolvedArgs = new ArrayList(args.length);
		for (int i = 0; i < args.length; i++) {
			if (args[i] instanceof MessageSourceResolvable) {
				resolvedArgs.add(getMessage((MessageSourceResolvable) args[i], locale));
			} else {
				resolvedArgs.add(args[i]);
			}
		}
		return resolvedArgs.toArray(new Object[resolvedArgs.size()]);
	}

	private String[] basenames = new String[0];

	private ClassLoader bundleClassLoader;

	private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

	/**
	 * Set the ClassLoader to load resource bundles with.
	 * <p>
	 * Default is the containing BeanFactory's {@link org.springframework.beans.factory.BeanClassLoaderAware bean ClassLoader}, or the default ClassLoader determined by {@link org.springframework.util.ClassUtils#getDefaultClassLoader()} if not running within a BeanFactory.
	 */
	public void setBundleClassLoader(ClassLoader classLoader) {
		this.bundleClassLoader = classLoader;
	}

	/**
	 * Return the ClassLoader to load resource bundles with.
	 * <p>
	 * Default is the containing BeanFactory's bean ClassLoader.
	 * @see #setBundleClassLoader
	 */
	protected ClassLoader getBundleClassLoader() {
		return (this.bundleClassLoader != null ? this.bundleClassLoader : this.beanClassLoader);
	}

	public void setBeanClassLoader(ClassLoader classLoader) {
		this.beanClassLoader = (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
	}

	/**
	 * Resolves the given message code as key in the registered resource bundles, returning the value found in the bundle as-is (without MessageFormat parsing).
	 */
	protected String resolveCodeWithoutArguments(String code) {
		String result = getMessageOrNull(code);

		return result;
	}

	/**
	 * Resolves the given message code as key in the registered resource bundles, using a cached MessageFormat instance per message code.
	 */
	protected MessageFormat resolveCode(String code) {
		MessageFormat messageFormat = getMessageFormat(code);

		return messageFormat;
	}

	/**
	 * Return a MessageFormat for the given code
	 * @param code the message code to retrieve
	 * @return the resulting MessageFormat, or <code>null</code> if no message defined for the given code
	 */
	protected MessageFormat getMessageFormat(String code) {
		String msg = getMessageOrNull(code);
		if (msg != null) {
			MessageFormat result = createMessageFormat(msg);
			return result;
		}

		return null;
	}

	public String getMessageOrNull(String code) {
		try {
			this.characterCoding = ((SysUser) ContextUtil.getResource("sessionLogin")).getDefCharacterCoding();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
		return LocaleUtil.getLocalText(characterCoding, code, null);
	}
}