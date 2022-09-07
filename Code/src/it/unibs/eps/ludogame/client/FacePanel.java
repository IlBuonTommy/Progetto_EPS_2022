package it.unibs.eps.ludogame.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class FacePanel extends JPanel {

	String[] colori = { "#E74C3C", "#0E5DF1", "#2AC503", "#E1E100" };

	// coordinate dei punti ricavati con un mio script python
	int[][] mat = { { 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 45, 45, 45, 45, 45, 45,
			45, 46, 46, 46, 46, 46, 46, 47, 47, 47, 47, 48, 48, 48, 48, 49, 49, 49, 50, 50, 50, 50, 51, 51, 52, 53, 53,
			54, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 69, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70,
			70, 70, 71, 71, 71, 71, 71, 71, 71, 71, 72, 72, 72, 72, 72, 73, 73, 73, 73, 73, 74, 74, 74, 74, 75, 75, 75,
			75, 75, 76, 76, 76, 76, 76, 77, 77, 77, 78, 78, 78, 79, 79, 79, 80, 80, 80, 80, 81, 81, 81, 81, 82, 82, 82,
			83, 83, 83, 84, 84, 84, 85, 85, 85, 86, 86, 86, 87, 87, 87, 88, 88, 88, 89, 89, 89, 90, 90, 90, 91, 91, 91,
			92, 92, 92, 93, 93, 94, 94, 94, 95, 95, 95, 96, 96, 96, 97, 97, 97, 98, 98, 98, 99, 99, 99, 99, 99, 99, 99,
			99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 100, 100,
			100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101,
			101, 102, 102, 102, 102, 102, 102, 103, 103, 103, 103, 103, 103, 103, 104, 104, 104, 104, 104, 104, 104,
			105, 105, 105, 105, 105, 105, 105, 105, 106, 106, 106, 106, 106, 106, 106, 106, 106, 107, 107, 107, 107,
			107, 107, 107, 107, 107, 107, 107, 107, 107, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108,
			108, 108, 108, 108, 109, 109, 109, 109, 109, 109, 109, 109, 109, 109, 110, 110, 110, 110, 110, 110, 110,
			110, 111, 111, 111, 111, 111, 111, 111, 112, 112, 112, 112, 112, 112, 112, 113, 113, 113, 113, 113, 113,
			114, 114, 114, 114, 114, 114, 114, 115, 115, 115, 115, 115, 116, 116, 116, 116, 116, 117, 117, 117, 117,
			117, 118, 118, 118, 118, 118, 119, 119, 119, 119, 119, 119, 120, 120, 120, 120, 120, 121, 121, 121, 121,
			122, 122, 122, 122, 123, 123, 123, 123, 124, 124, 124, 124, 125, 125, 125, 125, 125, 126, 126, 126, 126,
			127, 127, 127, 127, 128, 128, 128, 128, 129, 129, 129, 129, 130, 130, 130, 130, 131, 131, 131, 131, 131,
			132, 132, 132, 132, 132, 133, 133, 133, 133, 133, 134, 134, 134, 134, 134, 135, 135, 135, 135, 136, 136,
			136, 136, 136, 137, 137, 137, 138, 138, 138, 139, 139, 139, 140, 140, 140, 141, 141, 141, 142, 142, 142,
			143, 143, 143, 144, 144, 144, 144, 145, 145, 145, 146, 146, 146, 147, 147, 147, 148, 148, 148, 148, 148,
			148, 149, 149, 149, 149, 149, 149, 149, 149, 150, 150, 150, 150, 150, 151, 151, 151, 152, 152, 152, 153,
			153, 153, 154, 154, 154, 155, 155, 155, 156, 156, 156, 157, 157, 157, 157, 158, 158, 158, 158, 159, 159,
			159, 159, 160, 160, 160, 160, 161, 161, 161, 161, 161, 162, 162, 162, 162, 163, 163, 163, 163, 164, 164,
			164, 164, 165, 165, 165, 165, 166, 166, 166, 166, 167, 167, 167, 168, 168, 168, 169, 169, 169, 169, 169,
			170, 170, 170, 170, 170, 170, 170, 170, 171, 171, 171, 171, 171, 172, 172, 172, 173, 173, 173, 173, 173,
			174, 174, 174, 174, 174, 175, 175, 175, 175, 176, 176, 176, 176, 176, 177, 177, 177, 178, 178, 178, 179,
			179, 179, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 181, 181, 181, 181, 182, 182, 182,
			182, 183, 183, 183, 183, 184, 184, 184, 185, 185, 185, 185, 186, 186, 186, 186, 187, 187, 187, 188, 188,
			188, 188, 189, 189, 189, 190, 190, 190, 190, 191, 191, 191, 192, 192, 192, 192, 193, 193, 193, 193, 194,
			194, 194, 195, 195, 195, 195, 196, 196, 196, 196, 197, 197, 197, 197, 198, 198, 198, 198, 199, 199, 199,
			199, 200, 200, 200, 200, 201, 201, 201, 201, 202, 202, 202, 202, 203, 203, 203, 203, 204, 204, 204, 204,
			205, 205, 205, 205, 206, 206, 206, 206, 207, 207, 207, 207, 207, 207, 208, 208, 208, 208, 208, 208, 210,
			211, 212, 213, 214, 215, 216, 217, 218, 220, 221, 222, 223, 224, 225, 226, 227, 228 },
			{ 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 55, 56, 57, 58, 78, 79, 80, 52,
					53, 54, 81, 82, 83, 50, 51, 84, 85, 48, 49, 86, 87, 46, 47, 88, 45, 46, 89, 90, 44, 91, 92, 93, 94,
					94, 95, 95, 96, 97, 98, 98, 99, 100, 100, 101, 101, 101, 102, 102, 103, 61, 62, 63, 64, 65, 66, 67,
					68, 69, 70, 71, 72, 103, 58, 59, 60, 73, 74, 75, 76, 103, 56, 57, 77, 78, 104, 54, 55, 79, 80, 104,
					53, 81, 82, 104, 51, 52, 83, 84, 104, 50, 51, 84, 85, 105, 49, 86, 105, 48, 87, 105, 47, 88, 105,
					45, 46, 89, 105, 44, 45, 89, 105, 44, 90, 105, 43, 91, 105, 42, 91, 105, 41, 91, 106, 41, 92, 106,
					40, 92, 106, 40, 93, 106, 39, 93, 106, 39, 93, 106, 38, 93, 106, 38, 93, 106, 93, 106, 37, 93, 106,
					37, 93, 106, 37, 93, 106, 36, 93, 106, 36, 93, 106, 36, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78,
					79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 36, 61, 62, 63, 64, 65,
					66, 67, 92, 96, 97, 98, 107, 36, 57, 58, 59, 60, 91, 92, 93, 94, 95, 107, 36, 56, 90, 91, 92, 107,
					36, 56, 57, 58, 89, 90, 107, 59, 60, 61, 62, 88, 89, 107, 35, 63, 64, 65, 85, 86, 87, 107, 35, 65,
					66, 67, 82, 83, 84, 86, 107, 35, 68, 69, 70, 71, 72, 77, 78, 79, 80, 81, 85, 107, 35, 47, 48, 49,
					50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 84, 107, 35, 43, 44, 45, 60, 61, 62, 83, 84, 108, 35, 40,
					41, 42, 63, 64, 83, 108, 35, 38, 39, 65, 66, 83, 108, 35, 36, 37, 67, 68, 82, 108, 34, 35, 69, 70,
					82, 109, 32, 33, 35, 70, 71, 82, 109, 31, 35, 72, 82, 110, 29, 30, 73, 82, 110, 28, 29, 74, 82, 110,
					27, 28, 75, 82, 111, 26, 27, 75, 76, 82, 111, 25, 26, 76, 82, 111, 25, 77, 82, 111, 24, 78, 82, 112,
					23, 78, 83, 112, 23, 79, 83, 112, 22, 79, 80, 83, 113, 22, 80, 83, 113, 21, 81, 83, 113, 21, 81, 83,
					114, 20, 82, 84, 114, 20, 82, 84, 114, 20, 35, 82, 84, 115, 19, 35, 83, 84, 115, 19, 35, 83, 84,
					115, 19, 35, 83, 84, 116, 19, 35, 84, 116, 19, 35, 84, 116, 117, 19, 35, 117, 19, 35, 118, 19, 35,
					118, 19, 36, 119, 19, 36, 119, 20, 36, 120, 20, 36, 121, 20, 36, 121, 122, 21, 36, 122, 21, 36, 123,
					22, 36, 123, 22, 37, 120, 121, 122, 123, 23, 37, 108, 109, 110, 111, 112, 113, 23, 24, 37, 106, 107,
					24, 37, 105, 25, 37, 104, 25, 37, 104, 26, 37, 103, 27, 37, 103, 28, 38, 103, 28, 29, 38, 103, 29,
					30, 38, 103, 30, 31, 38, 103, 31, 32, 38, 104, 32, 33, 38, 105, 106, 33, 34, 106, 107, 35, 39, 108,
					109, 36, 39, 110, 111, 37, 38, 39, 112, 38, 39, 113, 114, 39, 40, 115, 41, 42, 116, 40, 42, 43, 115,
					116, 40, 44, 108, 109, 110, 111, 112, 113, 40, 45, 46, 105, 106, 40, 47, 105, 40, 48, 49, 106, 107,
					41, 49, 50, 108, 109, 41, 51, 110, 111, 41, 52, 53, 111, 112, 41, 54, 112, 55, 56, 113, 42, 57, 113,
					42, 58, 59, 104, 105, 106, 107, 108, 109, 110, 111, 112, 42, 60, 61, 104, 42, 61, 62, 103, 43, 63,
					64, 103, 43, 65, 103, 43, 66, 67, 103, 43, 68, 69, 103, 44, 69, 70, 44, 71, 72, 104, 44, 73, 104,
					44, 74, 75, 104, 44, 76, 104, 44, 77, 78, 105, 44, 78, 79, 105, 45, 80, 106, 45, 81, 82, 106, 45,
					82, 83, 106, 45, 84, 85, 107, 45, 85, 86, 107, 45, 87, 88, 108, 45, 88, 89, 108, 46, 90, 91, 108,
					46, 91, 92, 109, 46, 93, 94, 109, 46, 94, 95, 109, 46, 96, 97, 109, 46, 98, 99, 109, 46, 100, 101,
					102, 108, 109, 46, 103, 104, 105, 106, 107, 47, 47, 47, 47, 47, 47, 47, 47, 47, 48, 48, 48, 48, 48,
					48, 48, 48, 48 } };

	int[][] spirale = { { 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13,
			13, 13, 13, 13, 13, 13, 14, 14, 14, 14, 14, 14, 14, 14, 14, 15, 15, 15, 15, 15, 15, 15, 16, 16, 16, 16, 16,
			16, 17, 17, 17, 17, 17, 18, 18, 18, 18, 19, 19, 19, 19, 20, 20, 20, 20, 21, 21, 21, 21, 22, 22, 22, 22, 23,
			23, 23, 23, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25,
			25, 25, 25, 25, 25, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 27, 27, 27, 27, 27, 27, 27, 27, 27, 28, 28, 28,
			28, 28, 28, 28, 28, 28, 29, 29, 29, 29, 29, 29, 29, 29, 29, 30, 30, 30, 30, 30, 30, 30, 30, 31, 31, 31, 31,
			31, 31, 32, 32, 32, 32, 32, 33, 33, 33, 33, 33, 33, 33, 34, 34, 34, 34, 34, 35, 35, 35, 35, 36, 36, 36, 36,
			36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 38, 38, 38,
			38, 38, 38, 38, 38, 38, 38, 38, 38, 39, 39, 39, 39, 39, 39, 39, 39, 39, 40, 40, 40, 40, 40, 40, 40, 40, 41,
			41, 41, 41, 41, 41, 41, 41, 41, 42, 42, 42, 42, 42, 42, 42, 42, 42, 43, 43, 43, 43, 43, 43, 43, 43, 44, 44,
			44, 44, 44, 44, 44, 45, 45, 45, 45, 45, 45, 45, 46, 46, 46, 46, 46, 46, 46, 47, 47, 47, 47, 47, 47, 47, 47,
			47, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49,
			50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 52, 52, 52, 52, 52, 52, 52,
			52, 52, 52, 52, 52, 52, 53, 53, 53, 53, 53, 53, 53, 53, 53, 54, 54, 54, 54, 54, 54, 54, 54, 55, 55, 55, 55,
			55, 55, 55, 55, 55, 56, 56, 56, 56, 56, 56, 56, 56, 57, 57, 57, 57, 57, 57, 57, 57, 57, 58, 58, 58, 58, 58,
			58, 58, 58, 58, 59, 59, 59, 59, 59, 59, 59, 59, 59, 60, 60, 60, 60, 60, 60, 60, 60, 61, 61, 61, 61, 61, 61,
			61, 61, 61, 62, 62, 62, 62, 62, 62, 62, 62, 62, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 64, 64, 64, 64, 64,
			64, 64, 64, 64, 64, 64, 64, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 66, 66, 66, 66, 66, 66, 66,
			66, 66, 66, 66, 67, 67, 67, 67, 67, 67, 67, 68, 68, 68, 68, 68, 68, 68, 69, 69, 69, 69, 69, 69, 69, 70, 70,
			70, 70, 70, 70, 70, 70, 70, 70, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 72, 72, 72, 72, 72, 72, 72, 72,
			73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 75, 75, 75, 75, 75, 75, 75,
			75, 75, 75, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77,
			77, 77, 77, 77, 77, 78, 78, 78, 78, 78, 78, 78, 78, 78, 78, 79, 79, 79, 79, 79, 79, 79, 79, 79, 80, 80, 80,
			80, 80, 80, 81, 81, 81, 81, 81, 81, 81, 81, 82, 82, 82, 82, 82, 82, 82, 83, 83, 83, 83, 83, 83, 84, 84, 84,
			84, 84, 84, 84, 84, 84, 85, 85, 85, 85, 85, 85, 85, 85, 86, 86, 86, 86, 86, 86, 86, 86, 86, 87, 87, 87, 87,
			87, 87, 87, 87, 87, 87, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 88, 89, 89, 89, 89, 89, 89, 89, 89, 89,
			89, 89, 89, 89, 89, 89, 89, 89, 89, 89, 90, 90, 90, 90, 90, 91, 91, 91, 91, 92, 92, 92, 92, 92, 92, 93, 93,
			93, 93, 94, 94, 94, 94, 94, 95, 95, 95, 95, 95, 96, 96, 96, 96, 96, 96, 97, 97, 97, 97, 97, 97, 98, 98, 98,
			98, 98, 98, 98, 99, 99, 99, 99, 99, 99, 99, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 101, 101, 101,
			101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101, 101 },
			{ 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 43, 44, 45, 46, 47, 48, 49, 50, 51, 56, 57, 58, 59, 60, 61,
					62, 63, 64, 40, 41, 42, 43, 64, 65, 66, 67, 68, 37, 38, 39, 68, 69, 70, 71, 35, 36, 37, 71, 72, 73,
					33, 34, 74, 75, 76, 31, 32, 76, 77, 29, 30, 78, 79, 28, 29, 80, 81, 26, 27, 81, 82, 25, 26, 83, 84,
					24, 25, 84, 85, 23, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 85, 86, 22, 44, 45, 46, 47, 48, 49,
					59, 60, 61, 62, 63, 64, 86, 87, 21, 42, 43, 44, 64, 65, 66, 67, 87, 88, 20, 39, 40, 41, 67, 68, 69,
					88, 89, 19, 37, 38, 39, 69, 70, 71, 89, 90, 18, 19, 36, 37, 71, 72, 73, 90, 91, 17, 18, 34, 35, 73,
					74, 91, 92, 17, 33, 34, 75, 76, 92, 16, 32, 76, 77, 93, 15, 16, 30, 31, 77, 78, 94, 15, 29, 30, 79,
					94, 14, 29, 80, 95, 14, 28, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 81, 95, 96, 13, 27, 46, 47,
					48, 49, 59, 60, 61, 62, 82, 96, 13, 26, 27, 44, 45, 46, 63, 64, 65, 82, 83, 97, 12, 26, 42, 43, 65,
					66, 83, 84, 97, 12, 25, 41, 42, 67, 68, 84, 98, 11, 24, 25, 39, 40, 68, 69, 85, 98, 11, 24, 38, 39,
					70, 85, 86, 98, 99, 10, 11, 23, 37, 38, 71, 86, 99, 10, 23, 37, 72, 86, 87, 99, 10, 22, 23, 36, 73,
					87, 100, 10, 22, 35, 74, 87, 88, 100, 9, 10, 22, 34, 35, 74, 75, 88, 100, 9, 21, 34, 50, 51, 52, 53,
					54, 55, 56, 57, 58, 75, 88, 101, 9, 21, 34, 48, 49, 50, 59, 60, 76, 89, 101, 9, 21, 33, 47, 48, 61,
					62, 76, 89, 101, 9, 20, 21, 33, 46, 62, 63, 77, 89, 101, 8, 9, 20, 32, 33, 45, 63, 64, 77, 89, 90,
					101, 102, 8, 20, 32, 45, 64, 77, 78, 90, 102, 8, 20, 32, 44, 65, 78, 90, 102, 8, 20, 32, 44, 65, 66,
					78, 90, 102, 8, 20, 32, 44, 66, 78, 90, 102, 8, 20, 32, 43, 66, 78, 90, 91, 102, 8, 20, 32, 43, 67,
					79, 90, 91, 102, 8, 20, 32, 43, 67, 79, 90, 91, 102, 8, 20, 32, 44, 67, 79, 91, 102, 8, 20, 32, 44,
					67, 79, 91, 102, 103, 8, 20, 32, 44, 67, 79, 91, 102, 103, 8, 20, 32, 45, 67, 79, 90, 91, 102, 103,
					8, 20, 32, 33, 46, 54, 55, 67, 79, 90, 91, 102, 9, 20, 21, 33, 47, 48, 53, 54, 67, 79, 90, 91, 102,
					9, 21, 33, 49, 50, 51, 52, 66, 78, 90, 102, 9, 21, 34, 66, 78, 90, 102, 9, 21, 34, 66, 78, 90, 102,
					9, 22, 35, 65, 78, 90, 102, 10, 22, 35, 36, 64, 65, 77, 78, 90, 102, 10, 22, 23, 36, 37, 64, 77, 89,
					90, 101, 102, 10, 23, 37, 38, 63, 77, 89, 101, 10, 11, 23, 24, 38, 39, 62, 76, 89, 101, 11, 24, 39,
					40, 60, 61, 76, 88, 89, 101, 11, 24, 25, 41, 42, 59, 60, 75, 88, 101, 12, 25, 42, 43, 44, 56, 57,
					58, 75, 88, 100, 12, 26, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 74, 87, 88, 100, 13, 27,
					49, 50, 51, 52, 73, 74, 87, 100, 13, 27, 28, 72, 73, 86, 87, 99, 100, 14, 28, 29, 72, 86, 99, 14,
					15, 29, 30, 71, 85, 86, 99, 15, 30, 31, 69, 70, 85, 98, 16, 32, 68, 69, 84, 98, 16, 17, 33, 34, 67,
					68, 83, 84, 97, 17, 34, 35, 36, 65, 66, 83, 97, 18, 36, 37, 38, 63, 64, 65, 82, 96, 19, 38, 39, 40,
					61, 62, 63, 81, 82, 96, 20, 40, 41, 42, 43, 58, 59, 60, 61, 80, 81, 95, 21, 44, 45, 46, 47, 48, 49,
					50, 51, 52, 53, 54, 55, 56, 57, 79, 80, 94, 95, 21, 22, 78, 79, 94, 23, 77, 78, 93, 24, 25, 76, 77,
					92, 93, 25, 26, 75, 92, 27, 28, 73, 74, 91, 28, 29, 72, 73, 90, 30, 31, 70, 71, 89, 90, 32, 33, 68,
					69, 88, 89, 34, 35, 36, 65, 66, 67, 88, 36, 37, 38, 39, 63, 64, 65, 39, 40, 41, 42, 43, 58, 59, 60,
					61, 62, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58 } };

	int[][] mat1 = { { 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 42, 42, 42, 42, 42, 42, 42, 42, 42, 43, 43, 43, 43,
			43, 44, 44, 44, 44, 44, 45, 45, 45, 45, 46, 46, 46, 46, 47, 47, 47, 48, 48, 48, 49, 49, 49, 50, 50, 50, 51,
			51, 51, 52, 52, 52, 53, 53, 53, 54, 54, 54, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55,
			55, 55, 55, 56, 56, 56, 56, 56, 56, 56, 56, 56, 56, 56, 56, 56, 56, 56, 56, 56, 57, 57, 57, 57, 57, 57, 57,
			57, 57, 57, 57, 57, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 59, 59, 59, 59, 59, 59, 59, 59, 60, 60, 60, 60,
			60, 60, 60, 61, 61, 61, 61, 61, 61, 61, 61, 62, 62, 62, 62, 62, 62, 62, 63, 63, 63, 64, 64, 64, 64, 64, 64,
			64, 64, 64, 64, 64, 64, 64, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 66, 66, 66, 66, 66, 66, 66, 66,
			66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 67, 67, 67, 67, 67, 67, 67, 67, 67, 67, 67, 67, 67, 67, 68, 68,
			68, 68, 68, 68, 68, 68, 68, 68, 68, 68, 69, 69, 69, 69, 69, 69, 69, 69, 69, 70, 70, 70, 70, 70, 70, 70, 70,
			70, 71, 71, 71, 71, 71, 71, 71, 71, 72, 72, 72, 72, 72, 72, 72, 72, 73, 73, 73, 73, 73, 73, 73, 74, 74, 74,
			74, 74, 74, 74, 75, 75, 75, 75, 75, 75, 75, 75, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76,
			77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77,
			77, 78, 78, 78, 78, 78, 78, 78, 78, 78, 78, 78, 78, 78, 78, 78, 78, 78, 78, 79, 79, 79, 79, 79, 79, 79, 79,
			79, 80, 80, 81, 81, 82, 82, 83, 83, 83, 84, 84, 85, 85, 86, 86, 87, 87, 88, 88, 89, 89, 90, 90, 91, 91, 92,
			92, 93, 93, 94, 94, 95, 95, 96, 96, 97, 97, 98, 98, 99, 99, 99, 100, 100, 101, 101, 102, 102, 103, 103, 104,
			104, 105, 105, 106, 106, 107, 107, 108, 108, 109, 109, 110, 110, 111, 111, 112, 112, 113, 113, 113, 113,
			113, 113, 113, 114, 114, 114, 114, 114, 115, 115, 115, 115, 115, 116, 116, 116, 116, 117, 117, 117, 117,
			118, 118, 118, 118, 119, 119, 119, 119, 119, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 121, 121,
			122, 122, 123, 123, 124, 124, 124, 125, 125, 125, 126, 126, 127, 127, 128, 128, 128, 129, 129, 130, 130,
			130, 131, 131, 132, 132, 132, 133, 133, 133, 133, 133, 133, 133, 133, 133, 133, 134, 134, 134, 134, 134,
			134, 134, 134, 134, 134, 134, 134, 134, 134, 134, 134, 135, 135, 135, 135, 135, 135, 135, 135, 135, 135,
			135, 136, 136, 136, 136, 136, 136, 136, 136, 137, 137, 137, 137, 137, 137, 137, 137, 138, 138, 138, 138,
			138, 138, 138, 138, 139, 139, 139, 139, 139, 139, 139, 139, 139, 140, 140, 140, 140, 140, 140, 140, 140,
			140, 140, 140, 140, 141, 141, 141, 141, 141, 141, 142, 142, 142, 142, 142, 142, 142, 142, 142, 142, 142,
			142, 143, 143, 143, 143, 143, 143, 143, 143, 143, 143, 143, 143, 143, 143, 143, 143, 143, 144, 144, 144,
			144, 144, 144, 144, 144, 144, 144, 144, 144, 144, 144, 144, 144, 144, 144, 144, 144, 144, 144, 144, 144,
			144, 144, 144, 144, 144, 145, 145, 145, 145, 145, 146, 146, 146, 146, 146, 146, 147, 147, 147, 147, 147,
			148, 148, 148, 148, 148, 149, 149, 149, 149, 149, 149, 149, 150, 150, 150, 150, 150, 150, 150, 151, 151,
			151, 151, 151, 151, 151, 152, 152, 152, 152, 152, 152, 152, 152, 153, 153, 153, 153, 153, 153, 153, 153,
			153, 153, 153, 153, 153, 153, 153, 154, 154, 155, 155, 156, 156, 157, 157, 158, 158, 158, 159, 159, 160,
			160, 161, 161, 162, 162, 163, 163, 164, 164, 165, 165, 166, 166, 167, 167, 168, 168, 169, 169, 170, 170,
			171, 171, 172, 172, 173, 173, 174, 174, 175, 175, 175, 176, 176, 176, 176, 176, 177, 177, 177, 177, 177,
			177, 177, 178, 178, 178, 178, 178, 178, 178, 179, 179, 179, 179, 179, 179, 179, 179, 179, 179, 179, 179,
			180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180, 180 },
			{ 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 61, 62, 63, 64, 76, 77, 78, 79, 80, 59, 60, 81, 82, 83, 57,
					58, 84, 85, 86, 55, 56, 87, 88, 54, 55, 89, 90, 54, 91, 92, 53, 92, 93, 53, 93, 94, 52, 94, 95, 52,
					95, 96, 52, 96, 107, 52, 97, 107, 52, 97, 107, 52, 98, 107, 143, 144, 145, 146, 147, 148, 149, 150,
					151, 152, 153, 154, 155, 156, 157, 158, 52, 98, 107, 134, 135, 136, 137, 138, 139, 140, 141, 142,
					143, 158, 159, 160, 161, 53, 98, 107, 129, 130, 131, 132, 133, 134, 162, 163, 164, 53, 99, 108, 124,
					125, 126, 127, 128, 164, 165, 53, 99, 108, 120, 121, 122, 123, 166, 53, 99, 109, 117, 118, 119, 167,
					53, 100, 109, 114, 115, 116, 167, 168, 54, 100, 110, 111, 112, 113, 168, 54, 100, 169, 54, 66, 67,
					68, 69, 70, 71, 72, 73, 74, 75, 100, 169, 55, 62, 63, 64, 65, 66, 76, 77, 78, 79, 100, 169, 55, 60,
					61, 62, 79, 80, 81, 100, 135, 136, 137, 138, 139, 140, 141, 142, 143, 168, 169, 55, 58, 59, 82, 83,
					100, 132, 133, 134, 144, 145, 146, 166, 167, 56, 57, 83, 84, 101, 130, 131, 132, 147, 148, 165, 166,
					56, 85, 101, 129, 130, 149, 150, 163, 164, 57, 86, 101, 127, 128, 150, 151, 162, 163, 57, 87, 101,
					126, 127, 152, 160, 161, 58, 87, 88, 101, 125, 153, 158, 159, 59, 88, 101, 124, 154, 157, 158, 60,
					61, 88, 101, 123, 155, 156, 61, 62, 89, 101, 122, 153, 154, 156, 63, 64, 89, 101, 121, 122, 123,
					124, 125, 126, 127, 150, 151, 152, 157, 65, 66, 67, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87,
					88, 101, 128, 129, 130, 131, 132, 133, 148, 149, 150, 157, 158, 68, 69, 70, 71, 72, 73, 74, 75, 133,
					134, 135, 136, 137, 144, 145, 146, 147, 158, 100, 137, 138, 139, 140, 141, 142, 143, 159, 100, 159,
					100, 160, 100, 160, 100, 160, 161, 100, 161, 100, 161, 100, 161, 99, 162, 99, 162, 99, 162, 99, 162,
					99, 162, 99, 163, 98, 163, 98, 163, 98, 163, 98, 163, 98, 163, 98, 163, 97, 163, 164, 97, 164, 97,
					164, 97, 164, 97, 164, 97, 164, 96, 164, 96, 164, 96, 164, 96, 164, 96, 164, 96, 164, 96, 164, 96,
					164, 96, 103, 104, 105, 106, 107, 164, 96, 102, 103, 108, 164, 96, 102, 108, 163, 164, 96, 101, 108,
					163, 96, 101, 108, 163, 97, 101, 108, 163, 97, 101, 106, 107, 163, 98, 99, 100, 101, 102, 103, 104,
					105, 106, 162, 102, 162, 102, 162, 103, 161, 103, 104, 161, 104, 105, 160, 105, 160, 106, 159, 107,
					158, 159, 108, 158, 108, 109, 157, 109, 156, 110, 155, 156, 103, 104, 105, 107, 108, 109, 110, 111,
					154, 155, 90, 91, 92, 93, 94, 95, 96, 97, 98, 101, 102, 111, 112, 113, 114, 154, 88, 89, 98, 99,
					100, 112, 113, 114, 115, 116, 153, 86, 87, 113, 114, 117, 118, 119, 152, 85, 114, 115, 120, 121,
					122, 151, 152, 83, 84, 116, 117, 122, 123, 124, 151, 82, 83, 117, 118, 119, 125, 126, 127, 150, 81,
					120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 149, 81, 82, 83, 84, 130, 149, 81, 84, 85, 86, 87,
					124, 125, 126, 127, 128, 129, 148, 81, 82, 88, 89, 90, 91, 92, 117, 118, 119, 120, 121, 122, 123,
					128, 129, 147, 81, 82, 83, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108,
					109, 110, 111, 112, 113, 114, 115, 116, 127, 147, 81, 83, 125, 126, 146, 81, 84, 124, 125, 145, 146,
					81, 85, 122, 123, 145, 81, 86, 120, 121, 144, 81, 87, 88, 117, 118, 119, 144, 81, 88, 89, 114, 115,
					116, 143, 81, 90, 91, 111, 112, 113, 143, 82, 92, 93, 107, 108, 109, 110, 142, 82, 94, 95, 96, 97,
					98, 99, 100, 101, 102, 103, 104, 105, 106, 142, 82, 141, 82, 141, 82, 140, 83, 140, 83, 139, 140,
					83, 139, 84, 139, 84, 138, 84, 138, 84, 138, 85, 137, 85, 137, 85, 137, 85, 136, 86, 136, 86, 136,
					87, 135, 87, 135, 88, 134, 89, 134, 90, 133, 91, 131, 132, 92, 93, 128, 129, 130, 93, 94, 95, 125,
					126, 127, 128, 95, 96, 97, 122, 123, 124, 125, 98, 99, 100, 101, 102, 103, 116, 117, 118, 119, 120,
					121, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115 } };

	int[][] mat2 = { { 20, 20, 20, 20, 20, 20, 21, 21, 21, 21, 22, 22, 23, 23, 24, 24, 25, 25, 26, 26, 27, 28, 29, 29,
			30, 30, 31, 31, 32, 32, 33, 33, 34, 35, 35, 36, 36, 37, 37, 38, 38, 39, 40, 40, 41, 42, 42, 43, 43, 44, 44,
			45, 45, 46, 46, 47, 47, 48, 48, 49, 49, 50, 50, 51, 51, 52, 52, 53, 53, 54, 54, 55, 55, 56, 56, 57, 57, 58,
			58, 59, 59, 59, 59, 59, 59, 59, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 61, 61, 61, 61, 61,
			61, 61, 61, 61, 61, 61, 61, 62, 62, 62, 62, 62, 62, 62, 62, 62, 62, 63, 63, 63, 63, 63, 63, 63, 63, 64, 64,
			64, 64, 64, 64, 64, 65, 65, 65, 65, 65, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 67, 67, 67, 67, 67,
			67, 67, 67, 67, 67, 67, 68, 68, 68, 68, 68, 68, 68, 68, 69, 69, 69, 69, 69, 69, 69, 69, 70, 70, 70, 70, 70,
			70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71,
			71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72,
			72, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74,
			74, 74, 75, 75, 75, 75, 75, 75, 75, 75, 76, 76, 76, 76, 76, 76, 76, 76, 76, 77, 77, 77, 77, 77, 77, 77, 77,
			77, 77, 77, 77, 77, 78, 78, 78, 78, 78, 78, 78, 78, 78, 78, 78, 78, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79,
			79, 79, 79, 79, 79, 80, 80, 80, 80, 80, 81, 81, 81, 82, 82, 83, 83, 84, 84, 85, 85, 86, 86, 88, 88, 89, 89,
			90, 90, 91, 91, 92, 92, 93, 93, 93, 94, 94, 94, 95, 95, 95, 96, 96, 96, 97, 97, 97, 98, 98, 99, 99, 100,
			100, 100, 101, 101, 101, 102, 102, 102, 103, 103, 103, 103, 104, 104, 104, 104, 104, 105, 105, 105, 105,
			105, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 107, 107, 107, 107,
			107, 107, 107, 107, 107, 107, 107, 107, 107, 107, 107, 107, 107, 108, 108, 108, 108, 108, 108, 108, 109,
			109, 109, 110, 110, 110, 111, 111, 111, 112, 112, 112, 112, 113, 113, 113, 114, 114, 114, 115, 115, 115,
			115, 115, 115, 115, 115, 115, 115, 115, 115, 115, 115, 116, 116, 116, 116, 116, 116, 116, 116, 116, 116,
			117, 117, 117, 117, 117, 118, 118, 118, 118, 118, 118, 119, 119, 119, 119, 119, 120, 120, 120, 120, 120,
			120, 120, 120, 121, 121, 121, 121, 121, 121, 121, 121, 121, 121, 121, 121, 121, 121, 121, 121, 122, 122,
			122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 122, 123, 123, 123, 123, 123, 123, 123, 123,
			123, 123, 123, 123, 123, 123, 123, 123, 123, 123, 123, 123, 123, 123, 124, 124, 124, 124, 125, 125, 125,
			126, 126, 126, 127, 127, 128, 128, 128, 129, 129, 129, 130, 130, 130, 130, 130, 130, 130, 130, 130, 130,
			130, 130, 130, 130, 130, 130, 131, 131, 131, 131, 132, 132, 132, 133, 133, 133, 134, 134, 134, 134, 134,
			134, 134, 134, 134, 134, 135, 135, 135, 135, 136, 136, 136, 136, 137, 137, 137, 138, 138, 138, 138, 139,
			139, 139, 139, 140, 140, 140, 140, 141, 141, 141, 141, 142, 142, 142, 142, 143, 143, 143, 143, 143, 143,
			143, 144, 144, 144, 144, 144, 144, 144, 144, 145, 145, 145, 145, 145, 146, 147, 148, 149, 150, 150, 151,
			151, 152, 153, 154, 155, 155, 156, 157, 158, 159, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169,
			171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 180, 181, 182, 183, 184, 185, 185, 186, 186, 187, 188,
			188, 198, 198, 198, 198, 198, 198, 198, 198, 198, 198, 198, 198, 198, 198, 198, 198, 198, 198, 198, 198,
			198, 198, 199, 199, 199, 199, 199, 199, 199, 199, 199, 200, 200, 200, 200, 200, 200, 200, 200 },
			{ 94, 95, 96, 100, 101, 102, 92, 93, 103, 104, 91, 105, 91, 106, 90, 107, 90, 108, 90, 108, 109, 109, 90,
					110, 90, 110, 90, 111, 90, 111, 90, 111, 91, 91, 112, 91, 112, 92, 112, 92, 112, 113, 93, 113, 113,
					94, 113, 95, 113, 95, 113, 96, 114, 97, 114, 97, 114, 98, 114, 99, 114, 99, 114, 100, 114, 100, 114,
					101, 114, 101, 114, 102, 114, 102, 114, 103, 114, 103, 114, 76, 77, 78, 79, 80, 103, 114, 71, 72,
					73, 74, 75, 76, 77, 78, 84, 85, 86, 87, 103, 114, 68, 69, 70, 71, 72, 82, 83, 84, 89, 90, 91, 114,
					66, 67, 68, 86, 87, 92, 93, 94, 104, 114, 64, 65, 88, 89, 95, 96, 97, 113, 62, 91, 98, 99, 100, 103,
					113, 60, 61, 92, 93, 113, 58, 59, 77, 78, 79, 80, 81, 82, 83, 94, 112, 113, 57, 58, 70, 71, 72, 73,
					86, 87, 88, 95, 112, 56, 66, 67, 68, 89, 90, 96, 112, 54, 55, 63, 64, 65, 91, 96, 111, 53, 54, 61,
					62, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 92, 97, 111, 52, 53, 59, 60, 61, 62, 63, 69,
					70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 92, 98, 111, 51, 52, 57, 58, 59, 67, 68, 80, 81,
					82, 84, 85, 86, 92, 98, 110, 50, 51, 56, 57, 65, 66, 84, 85, 86, 87, 88, 92, 99, 110, 49, 50, 53,
					54, 55, 65, 87, 88, 89, 90, 91, 92, 99, 110, 48, 49, 52, 53, 65, 90, 99, 109, 48, 49, 50, 51, 52,
					65, 91, 100, 109, 47, 50, 65, 66, 85, 86, 87, 88, 89, 90, 91, 100, 109, 47, 48, 49, 66, 67, 81, 82,
					83, 84, 91, 100, 109, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 91, 100, 108, 89, 90, 91, 100,
					108, 90, 100, 108, 100, 108, 100, 108, 100, 108, 100, 108, 100, 108, 99, 109, 99, 109, 99, 109, 99,
					110, 98, 110, 49, 98, 110, 49, 98, 111, 49, 97, 111, 49, 97, 112, 96, 112, 113, 96, 113, 95, 114,
					49, 95, 115, 49, 94, 116, 49, 94, 117, 49, 94, 118, 119, 49, 89, 94, 119, 120, 49, 88, 89, 95, 121,
					49, 88, 91, 92, 93, 94, 95, 96, 97, 98, 104, 105, 106, 109, 122, 123, 50, 89, 90, 91, 92, 97, 98,
					99, 100, 101, 102, 103, 104, 105, 110, 123, 124, 50, 98, 106, 107, 108, 109, 125, 50, 99, 126, 50,
					100, 127, 51, 100, 128, 51, 100, 128, 129, 51, 100, 129, 52, 99, 130, 52, 88, 89, 90, 92, 93, 94,
					95, 98, 100, 101, 104, 105, 130, 53, 87, 88, 94, 95, 97, 98, 106, 107, 131, 53, 85, 86, 108, 131,
					54, 83, 84, 109, 110, 131, 54, 81, 82, 111, 132, 55, 78, 79, 80, 81, 112, 113, 132, 55, 74, 75, 76,
					78, 79, 95, 96, 97, 98, 99, 100, 101, 114, 118, 132, 56, 74, 75, 76, 77, 94, 102, 103, 104, 105,
					115, 116, 117, 119, 132, 57, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108,
					109, 110, 111, 112, 113, 132, 57, 110, 111, 132, 58, 111, 133, 59, 111, 133, 59, 111, 60, 110, 132,
					61, 109, 132, 62, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 132, 63, 93, 94,
					132, 64, 92, 132, 65, 92, 131, 66, 67, 93, 97, 98, 99, 100, 101, 102, 131, 68, 104, 105, 131, 69,
					70, 106, 130, 71, 107, 130, 72, 73, 107, 129, 74, 75, 107, 128, 76, 77, 107, 128, 79, 80, 107, 127,
					82, 83, 106, 126, 85, 86, 87, 104, 105, 125, 126, 89, 90, 91, 92, 101, 102, 103, 125, 96, 97, 98,
					99, 124, 123, 122, 121, 120, 118, 119, 117, 118, 116, 115, 114, 112, 113, 111, 110, 109, 107, 108,
					106, 105, 104, 103, 102, 101, 100, 100, 99, 99, 98, 98, 98, 98, 98, 98, 98, 99, 99, 99, 100, 100,
					101, 101, 102, 103, 104, 104, 105, 106, 107, 108, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
					15, 16, 17, 18, 19, 20, 21, 0, 1, 2, 3, 9, 15, 19, 20, 21, 0, 1, 2, 3, 10, 15, 20, 21 } };

	int[][] mat3 = { { 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 50, 50, 50,
			50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51,
			51, 51, 51, 51, 51, 51, 51, 52, 52, 52, 52, 52, 52, 53, 53, 53, 53, 53, 54, 54, 54, 55, 55, 55, 56, 56, 56,
			57, 57, 57, 58, 58, 58, 58, 59, 59, 59, 60, 60, 60, 61, 61, 61, 61, 61, 61, 61, 61, 62, 62, 62, 62, 62, 62,
			62, 62, 62, 62, 62, 62, 62, 62, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 64, 64,
			64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 66, 66, 66, 66, 66, 66,
			66, 66, 66, 66, 66, 67, 67, 67, 67, 67, 67, 67, 67, 67, 67, 67, 67, 68, 68, 68, 68, 68, 68, 68, 68, 68, 68,
			69, 69, 69, 69, 69, 69, 69, 69, 69, 69, 69, 70, 70, 70, 70, 70, 70, 70, 70, 70, 71, 71, 71, 71, 71, 71, 71,
			71, 71, 71, 71, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73,
			73, 73, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 75, 75, 75, 75, 75,
			75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 76, 76, 76, 76, 76, 76, 76, 76, 76,
			76, 76, 76, 76, 76, 76, 76, 76, 77, 77, 77, 77, 78, 78, 78, 78, 79, 79, 79, 80, 80, 80, 80, 80, 81, 81, 81,
			81, 82, 82, 82, 83, 83, 83, 83, 84, 84, 84, 85, 85, 85, 86, 86, 86, 86, 87, 87, 87, 88, 88, 88, 89, 89, 89,
			90, 90, 90, 90, 91, 91, 91, 91, 92, 92, 92, 92, 93, 93, 93, 93, 94, 94, 94, 94, 95, 95, 95, 95, 96, 96, 96,
			96, 97, 97, 97, 97, 98, 98, 98, 98, 99, 99, 99, 99, 99, 100, 100, 100, 100, 100, 100, 100, 100, 100, 101,
			101, 101, 101, 101, 101, 101, 102, 102, 102, 102, 102, 102, 103, 103, 103, 103, 103, 103, 103, 104, 104,
			104, 104, 104, 104, 104, 105, 105, 105, 105, 105, 105, 105, 105, 105, 105, 105, 106, 106, 106, 106, 106,
			106, 106, 106, 106, 106, 106, 106, 106, 106, 106, 107, 107, 107, 107, 107, 107, 107, 107, 108, 108, 108,
			109, 109, 109, 109, 110, 110, 110, 111, 111, 111, 112, 112, 113, 113, 114, 114, 115, 115, 115, 116, 116,
			116, 116, 117, 117, 117, 117, 117, 117, 117, 117, 117, 117, 117, 117, 117, 117, 117, 117, 117, 117, 117,
			117, 117, 117, 117, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118, 118,
			118, 118, 118, 118, 118, 119, 119, 119, 119, 119, 119, 119, 119, 119, 119, 119, 119, 119, 119, 119, 120,
			120, 120, 120, 120, 121, 121, 121, 121, 121, 121, 122, 122, 122, 122, 122, 122, 122, 122, 123, 123, 123,
			123, 123, 123, 123, 123, 123, 123, 123, 123, 123, 123, 123, 123, 123, 123, 123, 123, 123, 124, 124, 124,
			124, 124, 124, 124, 124, 124, 124, 124, 124, 124, 124, 124, 124, 124, 124, 124, 124, 124, 125, 125, 125,
			125, 125, 125, 125, 125, 125, 125, 125, 125, 125, 125, 125, 125, 125, 125, 125, 125, 125, 125, 125, 125,
			126, 126, 126, 126, 126, 126, 126, 126, 126, 126, 126, 126, 127, 127, 127, 127, 127, 128, 128, 128, 128,
			128, 129, 129, 129, 129, 129, 129, 130, 130, 130, 130, 130, 130, 131, 131, 131, 131, 131, 131, 132, 132,
			132, 132, 132, 132, 133, 133, 133, 133, 133, 133, 134, 134, 134, 134, 134, 134, 134, 135, 135, 135, 135,
			135, 135, 135, 135, 135, 135, 135, 135, 135, 136, 136, 136, 136, 136, 137, 137, 138, 138, 139, 139, 139,
			140, 140, 140, 141, 141, 141, 142, 142, 142, 143, 143, 143, 144, 144, 144, 145, 145, 145, 146, 146, 146,
			147, 147, 147, 148, 148, 148, 149, 149, 150, 150, 151, 151, 151, 151, 152, 152, 152, 152, 153, 153, 153,
			153, 154, 154, 154, 154, 154, 154, 154, 155, 155, 155, 155, 155, 155, 155, 198, 198, 198, 198, 198, 198,
			198, 198, 198, 198, 198, 198, 198, 198, 198, 198, 198, 198, 199, 199, 199, 199, 199, 199, 199, 199, 200,
			200, 200, 200, 200, 200, 200 },
			{ 56, 57, 58, 59, 60, 61, 62, 63, 64, 165, 53, 54, 55, 65, 66, 67, 68, 69, 70, 164, 165, 51, 52, 71, 72, 73,
					74, 75, 92, 93, 94, 95, 162, 163, 50, 51, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89,
					90, 91, 97, 98, 99, 100, 160, 161, 49, 50, 101, 102, 159, 160, 48, 49, 102, 103, 158, 48, 104, 157,
					47, 105, 156, 47, 105, 155, 47, 106, 154, 47, 106, 153, 154, 47, 106, 153, 47, 107, 152, 47, 68, 69,
					70, 71, 72, 107, 151, 48, 65, 66, 67, 73, 74, 75, 76, 107, 125, 126, 127, 150, 151, 49, 62, 63, 64,
					77, 78, 108, 120, 121, 122, 123, 124, 128, 129, 130, 131, 150, 49, 50, 61, 62, 79, 80, 108, 118,
					119, 132, 133, 149, 50, 51, 59, 60, 81, 82, 108, 116, 117, 134, 148, 51, 52, 58, 59, 82, 83, 108,
					115, 116, 135, 147, 53, 54, 57, 84, 85, 108, 109, 114, 115, 136, 146, 147, 54, 55, 56, 85, 86, 109,
					114, 136, 145, 146, 54, 55, 56, 57, 86, 87, 109, 113, 136, 144, 145, 53, 54, 58, 59, 88, 109, 113,
					137, 143, 52, 53, 60, 61, 89, 110, 112, 113, 137, 141, 142, 51, 52, 62, 63, 64, 90, 110, 112, 137,
					139, 140, 141, 50, 65, 66, 67, 68, 89, 90, 91, 110, 113, 137, 138, 139, 49, 68, 69, 70, 71, 72, 73,
					74, 84, 85, 86, 87, 88, 89, 110, 113, 134, 135, 136, 137, 47, 48, 74, 75, 76, 77, 78, 79, 80, 81,
					82, 83, 110, 114, 115, 127, 128, 129, 130, 131, 132, 133, 137, 46, 47, 111, 115, 116, 117, 118, 119,
					120, 121, 122, 123, 124, 125, 126, 127, 136, 45, 46, 111, 136, 44, 45, 111, 136, 43, 111, 136, 42,
					43, 111, 112, 136, 41, 42, 112, 136, 41, 112, 136, 40, 112, 135, 136, 39, 112, 135, 39, 112, 135,
					38, 39, 113, 135, 38, 113, 135, 38, 113, 135, 38, 113, 135, 38, 94, 113, 135, 38, 94, 113, 134, 38,
					93, 113, 134, 38, 93, 114, 134, 38, 92, 114, 134, 39, 92, 114, 134, 39, 91, 114, 134, 39, 91, 114,
					134, 40, 91, 114, 133, 40, 41, 91, 114, 133, 41, 91, 98, 99, 100, 101, 102, 114, 133, 42, 91, 97,
					102, 103, 114, 133, 43, 91, 96, 104, 113, 133, 44, 92, 96, 104, 105, 113, 133, 45, 93, 97, 105, 112,
					113, 133, 46, 47, 94, 95, 98, 99, 100, 104, 111, 112, 132, 47, 48, 96, 97, 98, 99, 100, 101, 102,
					103, 104, 109, 110, 111, 132, 49, 50, 104, 105, 106, 107, 108, 132, 51, 52, 132, 53, 54, 131, 132,
					55, 56, 131, 57, 58, 131, 59, 130, 60, 130, 61, 129, 62, 128, 129, 62, 63, 127, 128, 63, 93, 94, 95,
					96, 97, 98, 99, 100, 101, 102, 103, 111, 112, 113, 114, 115, 116, 117, 118, 119, 126, 127, 63, 64,
					86, 87, 88, 89, 90, 91, 92, 93, 103, 104, 105, 108, 109, 110, 111, 119, 120, 121, 125, 126, 64, 80,
					81, 82, 83, 84, 85, 105, 106, 107, 108, 122, 123, 124, 125, 64, 78, 79, 123, 124, 65, 79, 80, 121,
					122, 124, 65, 80, 81, 82, 119, 120, 121, 124, 65, 66, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93,
					94, 95, 96, 97, 117, 118, 119, 124, 66, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 98, 99, 100,
					101, 102, 114, 115, 116, 123, 66, 82, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 106,
					107, 108, 109, 110, 111, 112, 113, 122, 66, 82, 83, 100, 101, 102, 103, 104, 105, 106, 121, 122, 67,
					83, 105, 106, 121, 67, 84, 106, 107, 120, 67, 85, 86, 107, 118, 119, 68, 86, 87, 108, 117, 118, 68,
					87, 88, 108, 116, 117, 68, 89, 90, 108, 114, 115, 69, 91, 92, 109, 112, 113, 69, 93, 94, 95, 109,
					110, 111, 70, 96, 97, 98, 99, 100, 101, 104, 105, 106, 107, 108, 109, 70, 101, 102, 103, 109, 71,
					109, 72, 109, 72, 73, 109, 73, 74, 109, 74, 75, 109, 75, 76, 109, 76, 77, 109, 77, 78, 109, 78, 79,
					109, 79, 80, 109, 80, 81, 109, 81, 82, 108, 83, 108, 84, 107, 85, 86, 106, 107, 87, 88, 105, 106,
					88, 89, 90, 104, 90, 91, 92, 93, 101, 102, 103, 94, 95, 96, 97, 98, 99, 100, 0, 1, 2, 3, 4, 5, 6, 7,
					8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 0, 1, 2, 8, 9, 11, 16, 17, 0, 1, 2, 9, 13, 16, 17 } };

	int[][] head;

	int arroffsetty[] = { -20, 0, 0, +5 };
	int arroffsettx[] = { 0, -10, 0, 0 };

	String colore;

	private boolean turno = false;
	private int offsettx, offsetty;

	/**
	 * Create the panel.
	 */
	public FacePanel(int n) {

		switch (n) {
			case 0:
				this.head = this.mat;
				break;
			case 1:
				this.head = this.mat1;
				break;
			case 2:
				this.head = this.mat2;
				break;
			default:
				this.head = this.mat3;
		}

		this.offsetty = this.arroffsetty[n];
		this.offsettx = this.arroffsettx[n];

		this.colore = this.colori[n];

		setPreferredSize(new Dimension(80, 100));
		this.setTurno(false);

	}

	public void setTurno(boolean b) {
		this.turno = b;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (!this.turno) {
			g.setColor(Color.decode(this.colore));
			for (int i = 0; i < head[0].length; i++)
				g.fillOval(head[1][i] / 2 + this.offsettx, head[0][i] / 2 + this.offsetty, 2, 2);
		} else {
			g.setColor(Color.decode(this.colore));

			for (int i = 0; i < spirale[0].length; i++)
				g.fillOval(spirale[1][i] / 2 + 10, spirale[0][i] / 2 + 20, 2, 2);

		}

		g.setColor(Color.decode("#FFFFFF"));
		// inseirsco filtro
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
	}

}
