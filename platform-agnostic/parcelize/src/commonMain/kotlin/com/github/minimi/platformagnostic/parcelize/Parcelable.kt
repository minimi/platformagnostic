@file:Suppress("KDocUnresolvedReference")

package com.github.minimi.platformagnostic.parcelize

/**
 * Interface for classes whose instances can be written to
 * and restored from a [Parcel].  Classes implementing the Parcelable
 * interface must also have a non-null static field called `CREATOR`
 * of a type that implements the [Parcelable.Creator] interface.
 *
 * A typical implementation of Parcelable is:
 *
 * ```kotlin
 * class MyParcelable private constructor(`in`: Parcel) : Parcelable {
 *     private val mData: Int = `in`.readInt()
 *
 *     override fun describeContents(): Int {
 *         return 0
 *     }
 *
 *     override fun writeToParcel(out: Parcel, flags: Int) {
 *         out.writeInt(mData)
 *     }
 *
 *     companion object {
 *         val CREATOR: Parcelable.Creator<MyParcelable?>
 *                 = object : Parcelable.Creator<MyParcelable?> {
 *             override fun createFromParcel(`in`: Parcel): MyParcelable? {
 *                 return MyParcelable(`in`)
 *             }
 *
 *             override fun newArray(size: Int): Array<MyParcelable?> {
 *                 return arrayOfNulls(size)
 *             }
 *         }
 *     }
 * }
 * ```
 *
 * ```java
 * public class MyParcelable implements Parcelable {
 *     private int mData;
 *
 *     public int describeContents() {
 *         return 0;
 *     }
 *
 *     public void writeToParcel(Parcel out, int flags) {
 *         out.writeInt(mData);
 *     }
 *
 *     public static final Parcelable.Creator<MyParcelable> CREATOR
 *             = new Parcelable.Creator<MyParcelable>() {
 *         public MyParcelable createFromParcel(Parcel in) {
 *             return new MyParcelable(in);
 *         }
 *
 *         public MyParcelable[] newArray(int size) {
 *             return new MyParcelable[size];
 *         }
 *     };
 *
 *     private MyParcelable(Parcel in) {
 *         mData = in.readInt();
 *     }
 * }
 * ```
 */
expect interface Parcelable