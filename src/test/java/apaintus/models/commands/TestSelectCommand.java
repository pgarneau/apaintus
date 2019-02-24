package apaintus.models.commands;

class TestSelectCommand {
//    @Test
//    public void testCreateSelectCommandWithPreviousShape() {
//        CanvasController testCanvasController = mock(CanvasController.class);
//        DrawableShape testPreviousActiveShape = mock(DrawableShape.class);
//        DrawableShape testShape = mock(DrawableShape.class);
//        doCallRealMethod().when(testShape).getShapeType();
//        doCallRealMethod().when(testShape).setShapeType(any(ShapeType.class));
//        testShape.setShapeType(ShapeType.RECTANGLE);
//        SelectCommand test = new SelectCommand(testCanvasController,testPreviousActiveShape,testShape);
//
//        assertNotNull(test);
//    }
//
//    @Test
//    void testExecuteWithPreviousShape() {
//        CanvasController testCanvasController = mock(CanvasController.class);
//        DrawableShape testPreviousActiveShape = mock(DrawableShape.class);
//        DrawableShape testShape = mock(DrawableShape.class);
//
//        doCallRealMethod().when(testShape).getShapeType();
//        doCallRealMethod().when(testShape).setShapeType(any(ShapeType.class));
//        testShape.setShapeType(ShapeType.RECTANGLE);
//
//        doCallRealMethod().when(testShape).setSelected(anyBoolean());
//        doCallRealMethod().when(testPreviousActiveShape).setSelected(anyBoolean());
//        doCallRealMethod().when(testShape).isSelected();
//        doCallRealMethod().when(testPreviousActiveShape).isSelected();
//
//        SelectCommand test = new SelectCommand(testCanvasController,testPreviousActiveShape,testShape);
//
//        test.execute();
//
//        assertFalse(testPreviousActiveShape.isSelected());
//        assertTrue(testShape.isSelected());
//    }
//
//    @Test
//    void testUndoWithPreviousShape() {
//        CanvasController testCanvasController = mock(CanvasController.class);
//        DrawableShape testPreviousActiveShape = mock(DrawableShape.class);
//        DrawableShape testShape = mock(DrawableShape.class);
//
//        doCallRealMethod().when(testShape).getShapeType();
//        doCallRealMethod().when(testShape).setShapeType(any(ShapeType.class));
//        testShape.setShapeType(ShapeType.RECTANGLE);
//
//        doCallRealMethod().when(testShape).setSelected(anyBoolean());
//        doCallRealMethod().when(testPreviousActiveShape).setSelected(anyBoolean());
//        doCallRealMethod().when(testShape).isSelected();
//        doCallRealMethod().when(testPreviousActiveShape).isSelected();
//
//        SelectCommand test = new SelectCommand(testCanvasController,testPreviousActiveShape,testShape);
//
//        test.execute();
//        test.undo();
//
//        assertFalse(testShape.isSelected());
//        assertTrue(testPreviousActiveShape.isSelected());
//    }
//
//    @Test
//    void testRedoWithPreviousShape() {
//        CanvasController testCanvasController = mock(CanvasController.class);
//        DrawableShape testPreviousActiveShape = mock(DrawableShape.class);
//        DrawableShape testShape = mock(DrawableShape.class);
//
//        doCallRealMethod().when(testShape).getShapeType();
//        doCallRealMethod().when(testShape).setShapeType(any(ShapeType.class));
//        testShape.setShapeType(ShapeType.RECTANGLE);
//
//        doCallRealMethod().when(testShape).setSelected(anyBoolean());
//        doCallRealMethod().when(testPreviousActiveShape).setSelected(anyBoolean());
//        doCallRealMethod().when(testShape).isSelected();
//        doCallRealMethod().when(testPreviousActiveShape).isSelected();
//
//        SelectCommand test = new SelectCommand(testCanvasController,testPreviousActiveShape,testShape);
//
//        test.execute();
//        test.undo();
//        test.redo();
//
//        assertTrue(testShape.isSelected());
//        assertFalse(testPreviousActiveShape.isSelected());
//    }
//
//    @Test
//    public void testCreateSelectCommandWithNullPreviousShape() {
//        CanvasController testCanvasController = mock(CanvasController.class);
//        DrawableShape testShape = mock(DrawableShape.class);
//
//        doCallRealMethod().when(testShape).getShapeType();
//        doCallRealMethod().when(testShape).setShapeType(any(ShapeType.class));
//        testShape.setShapeType(ShapeType.RECTANGLE);
//
//        SelectCommand test = new SelectCommand(testCanvasController,null,testShape);
//
//        assertNotNull(test);
//    }
//
//    @Test
//    void testExecuteWithNullPreviousShape() {
//        CanvasController testCanvasController = mock(CanvasController.class);
//        DrawableShape testShape = mock(DrawableShape.class);
//
//        doCallRealMethod().when(testShape).getShapeType();
//        doCallRealMethod().when(testShape).setShapeType(any(ShapeType.class));
//        doCallRealMethod().when(testShape).setSelected(anyBoolean());
//        doCallRealMethod().when(testShape).isSelected();
//        testShape.setShapeType(ShapeType.RECTANGLE);
//
//        SelectCommand test = new SelectCommand(testCanvasController,null,testShape);
//        test.execute();
//
//        assertTrue(testShape.isSelected());
//    }
//
//    @Test
//    void testUndoWithNullPreviousShape() {
//        CanvasController testCanvasController = mock(CanvasController.class);
//        DrawableShape testShape = mock(DrawableShape.class);
//
//        doCallRealMethod().when(testShape).getShapeType();
//        doCallRealMethod().when(testShape).setShapeType(any(ShapeType.class));
//        testShape.setShapeType(ShapeType.RECTANGLE);
//
//        doCallRealMethod().when(testShape).setSelected(anyBoolean());
//        doCallRealMethod().when(testShape).isSelected();
//
//        SelectCommand test = new SelectCommand(testCanvasController,null,testShape);
//        test.execute();
//        test.undo();
//
//        assertFalse(testShape.isSelected());
//    }
//
//    @Test
//    void testRedoWithNullPreviousShape() {
//        CanvasController testCanvasController = mock(CanvasController.class);
//        DrawableShape testShape = mock(DrawableShape.class);
//
//        doCallRealMethod().when(testShape).getShapeType();
//        doCallRealMethod().when(testShape).setShapeType(any(ShapeType.class));
//        testShape.setShapeType(ShapeType.RECTANGLE);
//
//        doCallRealMethod().when(testShape).setSelected(anyBoolean());
//        doCallRealMethod().when(testShape).isSelected();
//
//        SelectCommand test = new SelectCommand(testCanvasController,null,testShape);
//
//        test.execute();
//        test.undo();
//        test.redo();
//
//        assertTrue(testShape.isSelected());
//    }
}