/*
 * Copyright ThinkTank Mathematics Limited 2006, 2007
 *
 * This file is free software: you can redistribute it and/or modify it under the terms of
 * the GNU General Public License as published by the Free Software Foundation, either
 * version 3 of the License, or (at your option) any later version. ThinkTank Mathematics
 * Limited designates this particular file as subject to the "Classpath" exception as provided
 * in the LICENSE file that accompanied this code.
 *
 * This file is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this file.
 * If not, see <http://www.gnu.org/licenses/>.
 * 
 * You should have received a copy of the "Classpath" exception along with this file. If
 * not, see <http://www.gnu.org/software/classpath/license.html>.
 */
package javax.microedition.location;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

/**
 * The <code>LandmarkStore</code> class provides methods to store, delete and retrieve
 * landmarks from a persistent landmark store. There is one default landmark store and
 * there may be multiple other named landmark stores. The implementation may support
 * creating and deleting landmark stores by the application. All landmark stores MUST be
 * shared between all J2ME applications and MAY be shared with native applications in the
 * terminal. Named landmark stores have unique names in this API. If the underlying
 * implementation allows multiple landmark stores with the same name, it must present them
 * with unique names in the API e.g. by adding some postfix to those names that have
 * multiple instances in order to differentiate them.
 * <p>
 * Because native landmark stores may be stored as files in a file system and file systems
 * have sometimes limitations for the allowed characters in file names, the
 * implementations MUST support all other Unicode characters in landmark store names
 * except the following list:
 * <ul>
 * <li>0x0000...0x001F control characters</li>
 * <li>0x005C <code>'\'</code></li>
 * <li>0x002F <code>'/'</code></li>
 * <li>0x003A <code>':'</code></li>
 * <li>0x002A <code>'*'</code></li>
 * <li>0x003F <code>'?'</code></li>
 * <li>0x0022 <code>'"'</code></li>
 * <li>0x003C <code>'<'</code></li>
 * <li>0x003E <code>'>'</code></li>
 * <li>0x007C <code>'|'</code></li>
 * <li>0x007F...0x009F control characters</li>
 * <li>0xFEFF Byte-order-mark</li>
 * <li>0xFFF0...0xFFFF</li>
 * </ul>
 * Support for the listed characters is not required and therefore applications are
 * strongly encouraged not to use the characters listed above in landmark store names in
 * order to ensure interoperability of the application on different platform
 * implementations.
 * <p>
 * The <code>Landmark</code>s have a name and may be placed in a category or several
 * categories. The category is intended to group landmarks that are of similar type to the
 * end user, e.g. restaurants, museums, etc. The landmark names are strings that identify
 * the landmark to the end user. The category names describe the category to the end user.
 * The language used in the names may be any and depends on the preferences of the end
 * user. The names of the categories are unique within a <code>LandmarkStore</code>.
 * However, the names of the landmarks are not guaranteed to be unique.
 * <code>Landmark</code>s with the same name can appear in multiple categories or even
 * several <code>Landmark</code>s with the same name in the same category.
 * <p>
 * The <code>Landmark</code> objects returned from the <code>getLandmarks</code>
 * methods in this class shall guarantee that the application can read a consistent set of
 * the landmark data valid at the time of obtaining the object instance, even if the
 * landmark information in the store is modified subsequently by this or some other
 * application.
 * <p>
 * The <code>Landmark</code> object instances can be in two states: <br>
 * <ul>
 * <li>initially constructed by an application </li>
 * <li>belongs to a <code>LandmarkStore</code> </li>
 * </ul>
 * A <code>Landmark</code> object belongs to a <code>LandmarkStore</code> if it has
 * been obtained from the <code>LandmarkStore</code> using <code>getLandmarks</code>
 * or if it has been added to the <code>LandmarkStore</code> using
 * <code>addLandmark</code>. A <code>Landmark</code> object is initially constructed
 * by an application when it has been constructed using the constructor but has not been
 * added to a <code>LandmarkStore</code> using <code>addLandmark</code>.
 * <p>
 * Note that the term "belongs to a <code>LandmarkStore</code>" is defined above in a
 * way that "belong to a <code>LandmarkStore</code>" has an different meaning than the
 * landmark "being currently stored in a <code>LandmarkStore</code>". According to the
 * above definition, a <code>Landmark</code> object instance may be in a state where it
 * is considered to "belong to a <code>LandmarkStore</code>" even when it is not stored
 * in that <code>LandmarkStore</code> (e.g. if the landmark is deleted from the
 * <code>LandmarkStore</code> using <code>deleteLandmark</code> method, the
 * <code>Landmark</code> object instance still is considered to "belong to this
 * <code>LandmarkStore</code>").
 * <p>
 * The landmark stores created by an application and landmarks added in landmark stores
 * persist even if the application itself is deleted from the terminal.
 * <p>
 * Accessing the landmark store may cause a <code>SecurityException</code>, if the
 * calling application does not have the required permissions. The permissions to read and
 * write (including add and delete) landmarks are distinct. An application having e.g. a
 * permission to read landmarks wouldn't necessarily have the permission to delete them.
 * The permissions (names etc.) for the MIDP 2.0 security framework are defined elsewhere
 * in this specification.
 */
public class LandmarkStore {

	/**
	 * Creates a new landmark store with a specified name. All LandmarkStores are shared
	 * between all J2ME applications and may be shared with native applications.
	 * Implementations may support creating landmark stores on a removable media. However,
	 * the Java application is not able to directly choose where the landmark store is
	 * stored, if the implementation supports several storage media. The implementation of
	 * this method may e.g. prompt the end user to make the choice if the implementation
	 * supports several storage media. If the landmark store is stored on a removable
	 * media, this media might be removed by the user possibly at any time causing it to
	 * become unavailable.
	 * <p>
	 * A newly created landmark store does not contain any landmarks.
	 * <p>
	 * Note that the landmark store name MAY be modified by the implementation when the
	 * store is created, e.g. by adding an implementation specific post-fix to
	 * differentiate stores on different storage drives as described in the class
	 * overview. Therefore, the application needs to use the listLandmarkStores method to
	 * discover the form the name was stored as. However, when creating stores to the
	 * default storage location, it is recommended that the implementation does not modify
	 * the store name but preserves it in the form it was passed to this method. It is
	 * strongly recommended that this method is implemented as character case preserving
	 * for the store name.
	 *
	 * @param storeName
	 *            the name of the landmark store to create
	 * @throws NullPointerException
	 *             if the parameter is null
	 * @throws IllegalArgumentException
	 *             if the name is too long or if a landmark store with the specified name
	 *             already exists
	 * @throws IOException
	 *             if the landmark store couldn't be created due to an I/O error
	 * @throws SecurityException
	 *             if the application does not have permissions to create a new landmark
	 *             store
	 * @throws LandmarkException
	 *             if the implementation does not support creating new landmark stores
	 */
	public static void createLandmarkStore(String storeName)
			throws NullPointerException, IllegalArgumentException, IOException,
			LandmarkException, SecurityException {
		try {
			com.openlapi.LandmarkStore.createLandmarkStore(storeName);
		} catch (com.openlapi.LandmarkException e) {
			throw new LandmarkException(e);
		}
	}

	/**
	 * Delete a landmark store with a specified name. All the landmarks and categories
	 * defined in the named landmark store are irrevocably removed. If a landmark store
	 * with the specified name does not exist, this method returns silently without any
	 * error.
	 * <p>
	 * Note that the landmark store names MAY be handled as either case-sensitive or
	 * case-insensitive (e.g. Unicode collation algorithm level 2). Therefore, the
	 * implementation MUST accept the names in the form returned by listLandmarkStores and
	 * MAY accept the name in other variations of character case.
	 *
	 * @param storeName
	 *            the name of the landmark store to delete
	 * @throws NullPointerException
	 *             if the parameter is null (the default landmark store can't be deleted)
	 * @throws IOException
	 *             if the landmark store couldn't be deleted due to an I/O error
	 * @throws SecurityException
	 *             if the appliction does not have permissions to delete a landmark store
	 * @throws LandmarkException
	 *             if the implementation does not support deleting landmark stores
	 */
	public static void deleteLandmarkStore(String storeName)
			throws NullPointerException, IOException, SecurityException,
			LandmarkException {
		try {
			com.openlapi.LandmarkStore.deleteLandmarkStore(storeName);
		} catch (com.openlapi.LandmarkException e) {
			throw new LandmarkException(e);
		}
	}

	/**
	 * Gets a LandmarkStore instance for storing, deleting and retrieving landmarks. There
	 * must be one default landmark store and there may be other landmark stores that can
	 * be accessed by name. Note that the landmark store names MAY be handled as either
	 * case-sensitive or case-insensitive (e.g. Unicode collation algorithm level 2).
	 * Therefore, the implementation MUST accept the names in the form returned by
	 * listLandmarkStores and MAY accept the name in other variations of character case.
	 *
	 * @param storeName
	 *            the name of the landmark store to open. if null, the default landmark
	 *            store will be returned
	 * @return the LandmarkStore object representing the specified landmark store or null
	 *         if a landmark store with the specified name does not exist.
	 * @throws SecurityException
	 *             if the application does not have a permission to read landmark stores
	 */
	public static LandmarkStore getInstance(String storeName)
			throws SecurityException {
		com.openlapi.LandmarkStore lStore = com.openlapi.LandmarkStore.getInstance(storeName);
		if (lStore == null) {
			return null;
		}
		return new LandmarkStore(lStore);
	}

	/**
	 * Lists the names of all the available landmark stores. The default landmark store is
	 * obtained from getInstance by passing null as the parameter. The null name for the
	 * default landmark store is not included in the list returned by this method. If
	 * there are no named landmark stores, other than the default landmark store, this
	 * method returns null.
	 * <p>
	 * The store names must be returned in a form that is directly usable as input to
	 * getInstance and deleteLandmarkStore.
	 *
	 * @return an array of landmark store names
	 * @throws SecurityException
	 *             if the application does not have the permission to access landmark
	 *             stores
	 * @throws IOException
	 *             if an I/O error occurred when trying to access the landmark stores
	 */
	public static String[] listLandmarkStores() throws SecurityException,
			IOException {
		return com.openlapi.LandmarkStore.listLandmarkStores();
	}

	final com.openlapi.LandmarkStore wrapped;

	/**
	 * Private constructor so that stores may only be created using the static methods of
	 * this class.
	 *
	 * @throws IOException
	 * @throws SecurityException
	 */
	private LandmarkStore(com.openlapi.LandmarkStore wrapped) {
		this.wrapped = wrapped;
	}

	/**
	 * Adds a category to this LandmarkStore. All implementations must support names that
	 * have length up to and including 32 characters. If the provided name is longer it
	 * may be truncated by the implementation if necessary.
	 *
	 * @param categoryName
	 *            name for the category to be added
	 * @throws IllegalArgumentException
	 *             if a category with the specified name already exists
	 * @throws NullPointerException
	 *             if the parameter is null
	 * @throws LandmarkException
	 *             if this LandmarkStore does not support adding new categories
	 * @throws IOException
	 *             if an I/O error occurs or there are no resources to add a new category
	 * @throws SecurityException
	 *             if the application does not have the permission to manage categories
	 */
	public void addCategory(String categoryName)
			throws IllegalArgumentException, NullPointerException,
			LandmarkException, IOException, SecurityException {
		try {
			wrapped.addCategory(categoryName);
		} catch (com.openlapi.LandmarkException e) {
			throw new LandmarkException(e);
		}
	}

	/**
	 * Adds a landmark to the specified group in the landmark store. If some textual
	 * String field inside the landmark object is set to a value that is too long to be
	 * stored, the implementation is allowed to automatically truncate fields that are too
	 * long.
	 * <p>
	 * However, the name field MUST NOT be truncated. Every implementation shall be able
	 * to support name fields that are 32 characters or shorter. Implementations may
	 * support longer names but are not required to. If an application tries to add a
	 * Landmark with a longer name field than the implementation can support,
	 * IllegalArgumentException is thrown.
	 * <p>
	 * When the landmark store is empty, every implementation is required to be able to
	 * store a landmark where each String field is set to a 30 character long string.
	 * <p>
	 * If the Landmark object that is passed as a parameter is an instance that belongs to
	 * this LandmarkStore, the same landmark instance will be added to the specified
	 * category in addition to the category/categories which it already belongs to. If the
	 * landmark already belongs to the specified category, this method returns with no
	 * effect. If the landmark has been deleted after obtaining it from getLandmarks, it
	 * will be added back when this method is called.
	 * <p>
	 * If the Landmark object that is passed as a parameter is an instance initially
	 * constructed by the application using the constructor or an instance that belongs to
	 * a different LandmarkStore, a new landmark will be created in this LandmarkStore and
	 * it will belong initially to only the category specified in the category parameter.
	 * After this method call, the Landmark object that is passed as a parameter belongs
	 * to this LandmarkStore.
	 *
	 * @param landmark
	 *            the landmark to be added
	 * @param category
	 *            where the landmark is added. null can be used to indicate that the
	 *            landmark does not belong to a category
	 * @throws SecurityException
	 *             if the application is not allowed to add landmarks
	 * @throws IllegalArgumentException
	 *             if the landmark has a longer name field than the implementation can
	 *             support or if the category is not null or one of the categories defined
	 *             in this LandmarkStore
	 * @throws IOException
	 *             if an I/O error happened when accessing the landmark store or if there
	 *             are no resources available to store this landmark
	 * @throws NullPointerException
	 *             if the landmark parameter is null
	 */
	public void addLandmark(Landmark landmark, String category)
			throws SecurityException, IllegalArgumentException, IOException,
			NullPointerException {
		wrapped.addLandmark(landmark.wrapped, category);
	}

	/**
	 * Removes a category from this LandmarkStore. The category will be removed from all
	 * landmarks that are in that category. However, this method will not remove any of
	 * the landmarks, only the associated category information from the landmarks. If a
	 * category with the supplied name does not exist in this LandmarkStore, the method
	 * returns silently with no error.
	 *
	 * @param categoryName
	 *            name for the category to be removed
	 * @throws NullPointerException
	 *             if the parameter is null
	 * @throws LandmarkException
	 *             if this LandmarkStore does not support deleting categories
	 * @throws IOException
	 *             if an I/O error occurs
	 * @throws SecurityException
	 *             if the application does not have the permission to manage categories
	 */
	public void deleteCategory(String categoryName)
			throws NullPointerException, LandmarkException, IOException,
			SecurityException {
		try {
			wrapped.deleteCategory(categoryName);
		} catch (com.openlapi.LandmarkException e) {
			throw new LandmarkException(e);
		}
	}

	/**
	 * Deletes a landmark from this LandmarkStore. This method removes the specified
	 * landmark from all categories and deletes the information from this LandmarkStore.
	 * The Landmark instance passed in as the parameter must be an instance that belongs
	 * to this LandmarkStore.
	 * <p>
	 * If the Landmark belongs to this LandmarkStore but has already been deleted from
	 * this LandmarkStore, then the request is silently ignored and the method call
	 * returns with no error. Note that LandmarkException is thrown if the Landmark
	 * instance does not belong to this LandmarkStore, and this is different from not
	 * being stored currently in this LandmarkStore.
	 *
	 * @param lm
	 *            the landmark to be deleted
	 * @throws SecurityException
	 *             if the application is not allowed to delete the landmark
	 * @throws LandmarkException
	 *             if the landmark instance passed as the parameter does not belong to
	 *             this LandmarkStore
	 * @throws IOException
	 *             if an I/O error happened when accessing the landmark store
	 * @throws NullPointerException
	 *             if the parameter is null
	 */
	public void deleteLandmark(Landmark lm) throws SecurityException,
			LandmarkException, IOException, NullPointerException {
		try {
			wrapped.deleteLandmark(lm.wrapped);
		} catch (com.openlapi.LandmarkException e) {
			throw new LandmarkException(e);
		}
	}

	/**
	 * Returns the category names that are defined in this LandmarkStore. The language and
	 * locale used for these names depends on the implementation and end user settings.
	 * The names shall be such that they can be displayed to the end user and have a
	 * meaning to the end user.
	 *
	 * @return an Enumeration containing Strings representing the category names. If there
	 *         are no categories defined in this LandmarkStore, an Enumeration with no
	 *         entries is returned.
	 */
	public Enumeration getCategories() {
		return wrapped.getCategories();
	}

	/**
	 * Lists all landmarks stored in the store.
	 *
	 * @return an Enumeration object containing Landmark objects representing all the
	 *         landmarks stored in this LandmarkStore or null if there are no landmarks in
	 *         the store
	 * @throws IOException
	 *             if an I/O error happened when accessing the landmark store
	 */
	public Enumeration getLandmarks() throws IOException {
		Enumeration lms = wrapped.getLandmarks();
		return wrapOpenLAPIEnumeration(lms);
	}

	/**
	 * Lists all the landmarks that are within an area defined by bounding minimum and
	 * maximum latitude and longitude and belong to the defined category, if specified.
	 * The bounds are considered to belong to the area. If minLongitude <= maxLongitude,
	 * this area covers the longitude range [minLongitude, maxLongitude]. If minLongitude >
	 * maxLongitude, this area covers the longitude range [-180.0, maxLongitude] and
	 * [minLongitude, 180.0).
	 * <p>
	 * For latitude, the area covers the latitude range [minLatitude, maxLatitude].
	 *
	 * @param category
	 *            the category of the landmark. null implies a wildcard that matches all
	 *            categories
	 * @param minLatitude
	 *            minimum latitude of the area. Must be within the range [-90.0, 90.0]
	 * @param maxLatitude
	 *            maximum latitude of the area. Must be within the range [minLatitude,
	 *            90.0]
	 * @param minLongitude
	 *            minimum longitude of the area. Must be within the range [-180.0, 180.0)
	 * @param maxLongitude
	 *            maximum longitude of the area. Must be within the range [-180.0, 180.0)
	 * @return an Enumeration containing all the matching Landmarks or null if no Landmark
	 *         matched the given parameters
	 * @throws IOException
	 *             if an I/O error happened when accessing the landmark store
	 * @throws IllegalArgumentException
	 *             if the minLongitude or maxLongitude is out of the range [-180.0,
	 *             180.0), or minLatitude or maxLatitude is out of the range [-90.0,90.0],
	 *             or if minLatitude > maxLatitude
	 */
	public Enumeration getLandmarks(String category, double minLatitude,
			double maxLatitude, double minLongitude, double maxLongitude)
			throws IOException, IllegalArgumentException {
		Enumeration lms = wrapped.getLandmarks(category, minLatitude,
				maxLatitude, minLongitude, maxLongitude);
		return wrapOpenLAPIEnumeration(lms);
	}

	/**
	 * Gets the Landmarks from the storage where the category and/or name matches the
	 * given parameters.
	 *
	 * @param category
	 *            the category of the landmark. null implies a wildcard that matches all
	 *            categories
	 * @param name
	 *            the name of the desired landmark. null implies a wildcard that matches
	 *            all the names within the category indicated by the category parameter
	 * @return an Enumeration containing all the matching Landmarks or null if no Landmark
	 *         matched the given parameters
	 * @throws IOException
	 *             if an I/O error happened when accessing the landmark store
	 */
	public Enumeration getLandmarks(String category, String name)
			throws IOException {
		Enumeration lms = wrapped.getLandmarks(category, name);
		return wrapOpenLAPIEnumeration(lms);
	}

	/**
	 * Removes the named landmark from the specified category. The Landmark instance
	 * passed in as the parameter must be an instance that belongs to this LandmarkStore.
	 * <p>
	 * If the Landmark is not found in this LandmarkStore in the specified category or if
	 * the parameter is a Landmark instance that does not belong to this LandmarkStore,
	 * then the request is silently ignored and the method call returns with no error. The
	 * request is also silently ignored if the specified category does not exist in this
	 * LandmarkStore.
	 * <p>
	 * The landmark is only removed from the specified category but the landmark
	 * information is retained in the store. If the landmark no longer belongs to any
	 * category, it can still be obtained from the store by passing null as the category
	 * to getLandmarks.
	 *
	 * @param lm
	 *            the landmark to be removed
	 * @param category
	 *            the category from which it will be removed.
	 * @throws SecurityException
	 *             if the application is not allowed to delete the landmark
	 * @throws IOException
	 *             if an I/O error happened when accessing the landmark store
	 * @throws NullPointerException
	 *             if either parameter is null
	 */
	public void removeLandmarkFromCategory(Landmark lm, String category)
			throws SecurityException, IOException, NullPointerException {
		wrapped.removeLandmarkFromCategory(lm.wrapped, category);
	}

	/**
	 * Updates the information about a landmark. This method only updates the information
	 * about a landmark and does not modify the categories the landmark belongs to. The
	 * Landmark instance passed in as the parameter must be an instance that belongs to
	 * this LandmarkStore.
	 * <p>
	 * This method can't be used to add a new landmark to the store.
	 *
	 * @param lm
	 *            the landmark to be updated
	 * @throws SecurityException
	 *             if the application is not allowed to update the landmark
	 * @throws LandmarkException
	 *             if the landmark instance passed as the parameter does not belong to
	 *             this LandmarkStore or does not exist in the store any more
	 * @throws IOException
	 *             if an I/O error happened when accessing the landmark store
	 * @throws NullPointerException
	 *             if the parameter is null
	 * @throws IllegalArgumentException
	 *             if the landmark has a longer name field than the implementation can
	 *             support
	 */
	public void updateLandmark(Landmark lm) throws SecurityException,
			LandmarkException, IOException, NullPointerException,
			IllegalArgumentException {
		try {
			wrapped.updateLandmark(lm.wrapped);
		} catch (com.openlapi.LandmarkException e) {
			throw new LandmarkException(e);
		}
	}

	/**
	 * Converts an Enumeration of com.openlapi.Landmark objects into an Enumeration of
	 * javax.microedition.location.Landmark objects.
	 *
	 * @param landmarks
	 * @return
	 */
	Enumeration wrapOpenLAPIEnumeration(Enumeration landmarks) {
		if (landmarks == null)
			return null;

		Vector lms = new Vector();
		while (landmarks.hasMoreElements()) {
			com.openlapi.Landmark landmark = (com.openlapi.Landmark) landmarks
					.nextElement();
			lms.addElement(new Landmark(landmark));
		}
		return lms.elements();
	}
}